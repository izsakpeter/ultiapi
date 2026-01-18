import 'dart:convert';
import 'package:flutter/material.dart';

import 'package:http/http.dart' as http;

import '../config.dart';

Future<List<String>> loggedinusersRequest(BuildContext context) async {
  try {
    final response = await http.post(
      getUri("loggedinusers"),
      headers: headers,
      body: {},
    );

    if (response.statusCode == 200) {
      final Map<String, dynamic> jsonResponse = jsonDecode(response.body);

      print('Response data: $jsonResponse');

      if (jsonResponse['isSuccess'] == true) {
        final List<dynamic> rawList = jsonResponse['loggedInUsers'];
        return rawList.map((e) => e.toString()).toList();
      }
    } else {
      print('Error: ${response.statusCode}');
    }
  } catch (e) {
    print('Exception: $e');
  }

  return [];
}
