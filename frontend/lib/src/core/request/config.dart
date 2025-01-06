final url = Uri.parse("http://localhost:8888/");
final headers = {'Content-Type': 'application/json'};

Uri getUri(String path) {
  return url.replace(path: "${url.path}$path");
}
