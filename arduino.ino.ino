#include <Arduino.h>
#include <WiFi.h>
#include <WebServer.h>
#include <ArduinoJson.h>

const char* ssid = "rulio";
const char* password = "244466666";
bool isDisponivel = true;
StaticJsonDocument<250> jsonDocument;
char buffer[250];
WebServer server(80);

#define LED 2

void setup() {
  // put your setup code here, to run once:
  Serial.begin(115200);
  pinMode(LED, OUTPUT);

  WiFi.begin(ssid, password);
  Serial.println("\nConnecting");

  while (WiFi.status() != WL_CONNECTED) {
    Serial.print(".");
    delay(100);
  }

  int i;

  for (i = 0; i < 4; ++i) {
    digitalWrite(LED, HIGH);
    delay(500);
    digitalWrite(LED, LOW);
    delay(500);
  }

  Serial.println("\nConnected to the WiFi network");
  Serial.print("Local ESP32 IP: ");
  Serial.println(WiFi.localIP());
  setup_routing();
}

void loop() {
  server.handleClient();
}

void setup_routing() {
  server.on("/piscar", disponivel);
  server.on("/valid", valid);
  server.on("/adicionarcomando", HTTP_POST, handlePost);
  server.begin();
}

void handlePost() {
  if (server.hasArg("plain") == false) {
    //handle error here
  }

  String body = server.arg("plain");
  deserializeJson(jsonDocument, body);
  
  // Get RGB components
  String comando = jsonDocument["comando"];

  isDisponivel = false;

  if (comando.equals("/ligarlampada")) {
    digitalWrite(LED, HIGH);
    delay(500);
    digitalWrite(LED, LOW);
    delay(500);
  }

  if (comando.equals("/abrircortina")) {
    digitalWrite(LED, HIGH);
    delay(500);
    digitalWrite(LED, LOW);
    delay(500);
    digitalWrite(LED, HIGH);
    delay(500);
    digitalWrite(LED, LOW);
  }

  isDisponivel = true;

  // Respond to the client
  server.send(200, "application/json", "{}");
}

void create_json(char* tag, char* value) {
  jsonDocument.clear();
  jsonDocument[tag] = value;
  serializeJson(jsonDocument, buffer);
}

void valid() {
  server.send(200);
}

void disponivel() {
  //create_json("disponivel", booleanToText(disponivel));
  server.send(200, "application/json", buffer);
}

String booleanToText(bool boolean) {
  return disponivel ? "true" : "false";
}