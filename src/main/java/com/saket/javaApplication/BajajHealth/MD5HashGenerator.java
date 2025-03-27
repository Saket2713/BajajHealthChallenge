package com.saket.javaApplication.BajajHealth;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5HashGenerator {

    public static void main(String[] args) {
        try {
            File jsonFile = new File("C://Users//Admin//Downloads//javaApplication (1)//javaApplication//src//main//java//com//saket//javaApplication//BajajHealth//input.json");
            if (!jsonFile.exists()) {
                System.err.println("input.json file not found");
                return;
            }

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonFile);

            JsonNode studentNode = rootNode.get("student");
            if (studentNode == null) {
                System.err.println("student node not found in JSON");
                return;
            }

            String firstName = studentNode.get("first_name").asText();
            String rollNumber = studentNode.get("roll_number").asText();

            if (firstName == null || rollNumber == null) {
                System.err.println("first_name or roll_number not found in JSON");
                return;
            }

            String concatenatedString = (firstName + rollNumber).toLowerCase();

            String md5Hash = generateMD5Hash(concatenatedString);

            try (FileWriter writer = new FileWriter("output.txt")) {
                writer.write(md5Hash);
            }

            System.out.println("MD5 hash generated and saved to output.txt");

        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private static String generateMD5Hash(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hashBytes = md.digest(input.getBytes());
        StringBuilder hexString = new StringBuilder();

        for (byte b : hashBytes) {
            hexString.append(String.format("%02x", b));
        }

        return hexString.toString();
    }
}



