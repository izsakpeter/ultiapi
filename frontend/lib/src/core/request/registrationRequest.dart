import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:frontend/src/core/request/config.dart';
import 'package:frontend/src/screen/login/login.dart';

import 'package:http/http.dart' as http;

Future<void> registrationRequest(
    String email, String user, String password, BuildContext context) async {
  final body = jsonEncode({
    'username': user,
    'password': password,
    'email': email,
  });

  try {
    final response = await http.post(
      getUri("registration"),
      headers: headers,
      body: body,
    );

    if (response.statusCode == 200) {
      // Handle successful response
      print('Response data: ${response.body}');

      Navigator.push(
        context,
        MaterialPageRoute(builder: (context) => const LoginScreen()),
      );
    } else {
      // Handle error response
      print('Error: ${response.statusCode}');
    }
  } catch (e) {
    print('Exception: $e');
  }
}
