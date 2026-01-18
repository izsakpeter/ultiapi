import 'package:flutter/material.dart';
import 'package:frontend/src/core/request/lobby/loggedinusersRequest.dart';
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

  Future<List<Map<String, dynamic>>> tableData() async {
    List<String> users = await loggedinusersRequest(context);

    return List.generate(users.length, (index) {
      return {
        'name': users[index],
        'status': 'online',
      };
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Lobby')),
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
                      const SnackBar(content: Text('Új asztal gomb megnyomva')),
                    );
                  },
                  child: const Text('Új asztal'),
                ),
                ElevatedButton(
                  onPressed: logout,
                  child: const Text('Kijelentkezés'),
                ),
              ],
            ),
            const SizedBox(height: 20),
            Expanded(
              child: FutureBuilder<List<Map<String, dynamic>>>(
                future: tableData(),
                builder: (context, snapshot) {
                  if (snapshot.connectionState == ConnectionState.waiting) {
                    return const Center(child: CircularProgressIndicator());
                  } else if (snapshot.hasError) {
                    return Center(
                        child: Text('Hiba történt: ${snapshot.error}'));
                  } else if (!snapshot.hasData || snapshot.data!.isEmpty) {
                    return const Center(
                        child: Text('Nincsenek bejelentkezett felhasználók.'));
                  }

                  final data = snapshot.data!;

                  return SingleChildScrollView(
                    scrollDirection: Axis.horizontal,
                    child: DataTable(
                      columns: const [
                        DataColumn(label: Text('Név')),
                        DataColumn(label: Text('Állapot')),
                      ],
                      rows: data.map((item) {
                        return DataRow(cells: [
                          DataCell(Text(item['name'].toString())),
                          DataCell(Text(item['status'].toString())),
                        ]);
                      }).toList(),
                    ),
                  );
                },
              ),
            ),
          ],
        ),
      ),
    );
  }
}
