package org.example;

import java.util.ArrayList;
import java.util.List;

public class JsonBuilder {
	static String jsonFormat = "{\n%s\n}";
	static ArrayList<String> fields = new ArrayList<String>();

	// static void add(String field, String value) {
	// String format = "\t\"%s\": %s";
	// fields.add(String.format(format, field, value));
	// }

	// static void add(String field, int value) {
	// String format = "\t\"%s\": %d";
	// fields.add(String.format(format, field, value));
	// }

	// static String getJson() {
	// String body = String.join(",\n", fields);
	// fields.clear();

	// return String.format(jsonFormat, body);
	// }

	static String getGoodWordJsonData(List<GoodWordEntry> goodWordList) {
		for (var entry : goodWordList) {
			fields.add(entry.toJson());
		}
		String body = String.join(",\n", fields);
		fields.clear();

		return String.format(jsonFormat, body);
	}

}