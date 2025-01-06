import 'package:flutter/material.dart';
import 'package:frontend/src/screen/hand.dart';

class TableScreen extends StatefulWidget {
  const TableScreen({super.key});

  @override
  _TableScreenState createState() => _TableScreenState();
}

class _TableScreenState extends State<TableScreen> {
  /* @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Table')),
      body: Center(
        child: Row(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: <Widget>[
            Expanded(
              child: Container(
                color: Colors.blue,
                child: const Center(
                  child: Text(
                    'menu',
                    style: TextStyle(color: Colors.white),
                    textAlign: TextAlign.center,
                  ),
                ),
              ),
            ),
            Expanded(
              child: Container(
                color: Colors.green,
                child: Transform.rotate(
                  angle: 270 * 3.1415927 / 180,
                  child: const Row(
                    mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                    children: <Widget>[
                      Hand(
                        isButtonDisabled: true,
                      ),
                    ],
                  ),
                ),
              ),
            ),
            Expanded(
              child: SizedBox(
                child: Column(
                  children: <Widget>[
                    Container(
                      color: Colors.pink,
                      child: const Row(
                        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                        children: <Widget>[
                          Hand(
                            isButtonDisabled: true,
                          ),
                        ],
                      ),
                    ),
                    Container(
                      color: Colors.black,
                      child: const Center(
                          child: Text('mid',
                              style: TextStyle(color: Colors.white))),
                    ),
                    Container(
                      color: Colors.yellow,
                      child: const Row(
                        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                        children: <Widget>[
                          Hand(
                            isButtonDisabled: false,
                          ),
                        ],
                      ),
                    ),
                  ],
                ),
              ),
            ),
            Expanded(
              child: Container(
                color: Colors.green,
                child: Transform.rotate(
                  angle: 90 * 3.1415927 / 180,
                  child: const Row(
                    mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                    children: <Widget>[
                      Hand(
                        isButtonDisabled: true,
                      ),
                    ],
                  ),
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}

import 'package:flutter/material.dart';

**************************************************************

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('3 Rows in 1 Column'),
        ),
        body: Column(
          children: <Widget>[
            const Row(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: <Widget>[
                Hand(
                  isButtonDisabled: true,
                ),
              ],
            ),
            Row(
              children: <Widget>[
                Expanded(
                  child: Container(
                    color: Colors.green,
                    height: 100,
                    child: const Center(
                      child: Text(
                        'Row 2',
                        style: TextStyle(color: Colors.white),
                      ),
                    ),
                  ),
                ),
              ],
            ),
            const Row(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: <Widget>[
                Hand(
                  isButtonDisabled: true,
                ),
              ],
            ),
          ],
        ),
      ),
    );
  }
}*/

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('1 Row with 5 Columns - Auto Width'),
        ),
        body: Center(
          child: IntrinsicWidth(
            child: Row(
              children: <Widget>[
                Container(
                  color: Colors.red,
                  child: const Text('Column 1',
                      style: TextStyle(color: Colors.white)),
                ),
                Container(
                  color: Colors.green,
                  child: const Text('Column 2',
                      style: TextStyle(color: Colors.white)),
                ),
                Container(
                  color: Colors.blue,
                  child: const Text('Column 3',
                      style: TextStyle(color: Colors.white)),
                ),
                Container(
                  color: Colors.green,
                  child: Transform.rotate(
                    angle: 270 * 3.1415927 / 180,
                    child: const Row(
                      mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                      children: <Widget>[
                        Hand(
                          isButtonDisabled: true,
                        ),
                      ],
                    ),
                  ),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
