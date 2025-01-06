import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:frontend/src/screen/lobby/lobby.dart';
import 'package:frontend/src/screen/table/table.dart';

import 'config.dart';
import 'package:http/http.dart' as http;

Future<void> loginRequest(
    String user, String password, BuildContext context) async {
  Navigator.push(
    context,
    MaterialPageRoute(builder: (context) => const LobbyScreen()),
  );

  final body = jsonEncode({
    'username': user,
    'password': password,
  });

  try {
    final response = await http.post(
      getUri("login"),
      headers: headers,
      body: body,
    );

    if (response.statusCode == 200) {
      // Handle successful response
      print('Response data: ${response.body}');

      Navigator.push(
        context,
        MaterialPageRoute(builder: (context) => const TableScreen()),
        // MaterialPageRoute(builder: (context) => LobbyScreen()),
      );
    } else {
      // Handle error response
      print('Error: ${response.statusCode}');
    }
  } catch (e) {
    print('Exception: $e');
  }
}
