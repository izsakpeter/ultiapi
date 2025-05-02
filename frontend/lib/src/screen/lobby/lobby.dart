import 'package:flutter/material.dart';
import 'package:frontend/src/core/request/logoutRequest.dart';

class LobbyScreen extends StatefulWidget {
  const LobbyScreen({super.key});

  @override
  LobbyScreenState createState() => LobbyScreenState();
}

class LobbyScreenState extends State<LobbyScreen> {
  void logout() {
    logoutRequest("izsakp", context);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          children: [
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: [
                ElevatedButton(
                  onPressed: () {
                    ScaffoldMessenger.of(context).showSnackBar(
                      const SnackBar(content: Text('Button 1 Pressed')),
                    );
                  },
                  child: const Text('új asztal'),
                ),
                ElevatedButton(
                  onPressed: logout,
                  child: const Text('kijelentkezés'),
                ),
              ],
            ),
            const SizedBox(height: 20),
            DataTable(
              columns: const [
                DataColumn(label: Text('Column 1')),
                DataColumn(label: Text('Column 2')),
              ],
              rows: const [
                DataRow(cells: [
                  DataCell(Text('Row 1, Col 1')),
                  DataCell(Text('Row 1, Col 2'))
                ]),
                DataRow(cells: [
                  DataCell(Text('Row 2, Col 1')),
                  DataCell(Text('Row 2, Col 2'))
                ]),
              ],
            ),
          ],
        ),
      ),
    );
  }
}
