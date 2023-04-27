#include <Arduino.h>
#include <WiFi.h>
#include <WebServer.h>
#include <ArduinoJson.h>

const char* ssid = "Wellington - TELnet";
const char* password = "wy25300353";
bool isDisponivel = true;
StaticJsonDocument<250> jsonDocument;
char buffer[250];
WebServer server(80);

#define LED 2
#define LAMPADA 16
#define CORTINA 17

const int freq = 5000;
const int ledChannel = 0;
const int resolution =  8;

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

if (WiFi.status() == WL_CONNECTED){
  Serial.print("\nConnected to: ");
  Serial.print(ssid);

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

// configure LED PWM functionalitites
  ledcSetup(ledChannel, freq, resolution);
  
// attach the channel to the GPIO to be controlled
  ledcAttachPin(CORTINA, ledChannel);



  if (server.hasArg("plain") == false) {
    //handle error here
  }

  String body = server.arg("plain");
  deserializeJson(jsonDocument, body);
  
  // Get RGB components
  String comando = jsonDocument["comando"];

  isDisponivel = false;

//Ligar e desligar lampada

  if (comando.equals("/ligarlampada")) {
    digitalWrite(LAMPADA, HIGH);
  }

  if (comando.equals("/desligarlampada")){
    digitalWrite(LAMPADA, LOW);
  }

//Abrir e fechar cortina

  if (comando.equals("/abrircortina")) {
    //digitalWrite(CORTINA, HIGH);
    //delay(500);
     // increase the LED brightness
    for(int dutyCycle = 0; dutyCycle <= 255; dutyCycle++){   
      // changing the LED brightness with PWM
      ledcWrite(ledChannel, dutyCycle);
      delay(15);
      ledcWrite(ledChannel, LOW);
    }
  }

  if (comando.equals("/fecharcortina")) {
    // decrease the LED brightness
    for(int dutyCycle = 255; dutyCycle >= 0; dutyCycle--){
      // changing the LED brightness with PWM
      ledcWrite(ledChannel, dutyCycle);   
      delay(15);
    }
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