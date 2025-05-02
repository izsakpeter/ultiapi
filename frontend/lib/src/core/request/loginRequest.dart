import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:frontend/src/screen/lobby/lobby.dart';

import 'config.dart';
import 'package:http/http.dart' as http;

Future<void> loginRequest(
    String email, String password, BuildContext context) async {
  Navigator.push(
    context,
    MaterialPageRoute(builder: (context) => const LobbyScreen()),
  );

  final body = jsonEncode({
    'email': email,
    'password': password,
  });

  try {
    final response = await http.post(
      getUri("login"),
      headers: headers,
      body: body,
    );

    if (response.statusCode == 200) {
      // Decode JSON response
      final Map<String, dynamic> jsonResponse = jsonDecode(response.body);

      print('Response data: $jsonResponse');

      // Check if 'isSuccess' exists and is true
      if (jsonResponse['isSuccess'] == true) {
        print('+++++++++++++++++++++++++');
        Future.microtask(() {
          Navigator.push(
            context,
            MaterialPageRoute(builder: (context) => const LobbyScreen()),
          );
        });
      }
    } else {
      // Handle error response
      print('Error: ${response.statusCode}');
    }
  } catch (e) {
    print('Exception: $e');
  }
}

extension on String {
  get isSuccess => "";
}
