package controller;

import db.DBConnection;
import view.AIInsightsView;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AIInsightsController {

    private AIInsightsView aiView;

    public AIInsightsController(AIInsightsView aiView) {
        this.aiView = aiView;

        // Button Click Event
        this.aiView.getBtnGenerate().addActionListener(e -> generateInsights());
    }

    private void generateInsights() {
        // Show loading message
        aiView.setInsightsText("Thinking... Analyzing your sales data. Please wait...");

        // Run AI request in a background thread so the UI doesn't freeze
        new Thread(() -> {
            try {
                // 1. Fetch Sales Data from Database
                String salesData = getSalesDataFromDB();
                if (salesData.isEmpty()) {
                    aiView.setInsightsText("Not enough sales data to generate insights. Please do some sales first!");
                    return;
                }

                // 2. Prepare the prompt for the AI
                String promptText = "You are an expert business analyst for a retail store. Based on this recent sales data: ["
                        + salesData + "] Give me 3 short, actionable tips to improve my business. Be very brief.";

                // 3. Prepare JSON Payload for Ollama API (Assuming 'llama3' model is installed)
                // Note: Make sure Ollama is running in the background!
                String jsonPayload = "{\"model\": \"llama3\", \"prompt\": \"" + promptText + "\", \"stream\": false}";

                // 4. Send HTTP POST Request to Local Ollama API
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("http://localhost:11434/api/generate"))
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
                        .build();

                // 5. Get Response
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                // 6. Extract the actual text from the JSON response
                String responseBody = response.body();
                String insightText = extractTextFromJson(responseBody);

                // 7. Display the result on the screen
                aiView.setInsightsText("🧠 AI Business Insights:\n\n" + insightText);

            } catch (Exception ex) {
                ex.printStackTrace();
                aiView.setInsightsText("Connection Error! Could not connect to Local AI.\n\n" +
                        "Please ensure that:\n" +
                        "1. Ollama is installed on your PC.\n" +
                        "2. Ollama is running in the background.\n" +
                        "3. You have downloaded the 'llama3' model (Command: ollama run llama3).\n\n" +
                        "Error Details: " + ex.getMessage());
            }
        }).start();
    }

    // Helper Method to read database and create a summary sentence
    private String getSalesDataFromDB() {
        StringBuilder data = new StringBuilder();
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            Statement stmt = conn.createStatement();

            // Get top 5 selling items
            String sql = "SELECT item_code, SUM(quantity) as qty FROM sales_items GROUP BY item_code ORDER BY qty DESC LIMIT 5";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                data.append("Item ").append(rs.getString("item_code"))
                        .append(" sold ").append(rs.getInt("qty")).append(" units, ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data.toString();
    }

    // Helper Method to extract the "response" field from JSON without using external libraries
    private String extractTextFromJson(String json) {
        try {
            String[] parts = json.split("\"response\":\"");
            if (parts.length > 1) {
                String afterResponse = parts[1];
                int endIndex = afterResponse.indexOf("\",\"");
                if (endIndex != -1) {
                    // Replace special characters to display properly
                    return afterResponse.substring(0, endIndex)
                            .replace("\\n", "\n")
                            .replace("\\\"", "\"")
                            .replace("\\*", "*");
                }
            }
            return "Unexpected AI Response format.";
        } catch (Exception e) {
            return "Failed to parse AI output.";
        }
    }
}