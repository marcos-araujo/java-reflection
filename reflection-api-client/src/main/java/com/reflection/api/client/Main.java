package com.reflection.api.client;

import java.util.Objects;
import java.util.Scanner;

import com.reflection.api.ReflectionAPI;
import com.reflection.api.client.dao.ProductDAO;
import com.reflection.api.client.dao.ProductDAOMock;

public class Main {

	public static void main(String[] args) {

		try (Scanner s = new Scanner(System.in)) {
			String url = s.nextLine();

			ReflectionAPI reflectionAPI = configReflectionAPI();

			while (!Objects.equals(url, "exit")) {
				Object response = reflectionAPI.execute(url);
				System.out.println("Response: " + response);
				url = s.nextLine();
			}

		}

	}

	public static ReflectionAPI configReflectionAPI() {
		ReflectionAPI alurator = new ReflectionAPI("com.reflection.api.client.controller.");
		alurator.registerClasses(ProductDAO.class, ProductDAOMock.class);
		return alurator;
	}

}