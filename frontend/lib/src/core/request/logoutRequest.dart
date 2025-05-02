import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:frontend/src/screen/login/login.dart';

import 'config.dart';

Future<void> logoutRequest(String name, BuildContext context) async {
  final body = jsonEncode({'name': name});

  try {
    final response = await http.post(
      getUri("logout"),
      headers: headers,
      body: body,
    );

    if (response.statusCode == 200) {
      // Decode JSON response
      final Map<String, dynamic> jsonResponse = jsonDecode(response.body);

      print('Logout Response: $jsonResponse');

      if (jsonResponse['isSuccess'] == true) {
        // Ensure widget is still mounted before navigating
        if (context.mounted) {
          Navigator.pushReplacement(
            context,
            MaterialPageRoute(builder: (context) => const LoginScreen()),
          );
        }
      } else {
        print('Logout failed: ${jsonResponse['message']}');
      }
    } else {
      print('Error: ${response.statusCode}');
    }
  } catch (e) {
    print('Exception during logout: $e');
  }
}
