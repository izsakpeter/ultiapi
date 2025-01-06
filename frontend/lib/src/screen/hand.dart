import 'package:flutter/material.dart';

class Hand extends StatelessWidget {
  final bool isButtonDisabled;

  const Hand({super.key, required this.isButtonDisabled});
  @override
  Widget build(BuildContext context) {
    return Row(
      mainAxisAlignment: MainAxisAlignment.center,
      children: List.generate(10, (index) {
        return Padding(
          padding: const EdgeInsets.all(0.0),
          child: ElevatedButton(
            onPressed: isButtonDisabled
                ? () {}
                : () {
                    // Add your onPressed code here!
                    print('Button $index pressed');
                  },
            child: Text('Button $index'),
          ),
        );
      }),
    );
  }
}
