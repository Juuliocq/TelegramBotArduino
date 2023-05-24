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

//Definição dos pinos
#define LED 2 //LED de Debug

//Pinos dos cômodos inferiores
#define LAMP_GAR1 16 //Garagem 
#define LAMP_BANH1 17 //Banheiro de baixo
#define LAMP_SALA 18 //

//Pinos dos cômodos superiores
#define LAMP_QUART1 19
#define LAMP_QUART2 22
#define LAMP_BANH2 23

/*
#define VENT_1  1
Propriedades do PWM para o ventilador
const int freq = 5000;
const int ledChannel = 0;
const int resolution = 8;
*/

void setup() {
  // put your setup code here, to run once:
  Serial.begin(115200);
  pinMode(LED, OUTPUT);
  pinMode(LAMP_GAR1, OUTPUT);
  pinMode(LAMP_BANH1, OUTPUT);
  pinMode(LAMP_SALA, OUTPUT);

  pinMode(LAMP_QUART1, OUTPUT);
  pinMode(LAMP_QUART2, OUTPUT);
  pinMode(LAMP_BANH2, OUTPUT);
  //pinMode(VENT_1, OUTPUT);


  WiFi.begin(ssid, password);
  Serial.println("\nConnecting");

  while (WiFi.status() != WL_CONNECTED) {
    Serial.print(".");
    delay(100);
  }


if (WiFi.status() == WL_CONNECTED){
  Serial.println("\nConnected to: ");
  Serial.print(ssid);

}


  int i;

  for (i = 0; i < 4; ++i) {
    digitalWrite(LED, HIGH);
    delay(500);
    digitalWrite(LED, LOW);
    delay(500);
  }

  //Serial.println("\nConnected to the WiFi network \n");
  Serial.println("\nLocal ESP32 IP: ");
  Serial.print(WiFi.localIP());
  setup_routing();
/*
  // configure LED PWM functionalitites
  ledcSetup(ledChannel, freq, resolution);
  
  // attach the channel to the GPIO to be controlled
  ledcAttachPin(VENT_1, ledChannel);
*/
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


//Comandos dos cômodos inferiores
  //Ligar e desligar LAMP_GAR1
  if (comando.equals("/acender_garagem")) {
    digitalWrite(LAMP_GAR1, HIGH);
    Serial.println("\nLampada 1 ligada");
  }

  if (comando.equals("/apagar_garagem")){
    digitalWrite(LAMP_GAR1, LOW);
    Serial.print("\nLampada 1 desligada");
  }

  //Ligar e desligar LAMP_BANH1
  if (comando.equals("/acender_banheiro_1")) {
    digitalWrite(LAMP_BANH1, HIGH);
    Serial.print("\nLampada 2 ligada");
  }

  if (comando.equals("/apagar_banheiro_1")){
    digitalWrite(LAMP_BANH1, LOW);
    Serial.print("\nLampada 2 desligada");
  }

  //Ligar e desligar LAMP_SALA
  if (comando.equals("/acender_sala")) {
    digitalWrite(LAMP_SALA, HIGH);
    Serial.print("\nLampada 3 ligada");
  }

  if (comando.equals("/apagar_sala")){
    digitalWrite(LAMP_SALA, LOW);
    Serial.print("\nLampada 3 desligada");
  }

//Comandos dos cômodos superiores
    //Ligar e desligar LAMP_QUART1
  if (comando.equals("/acender_quarto_1")) {
    digitalWrite(LAMP_QUART1, HIGH);
    Serial.print("\nLampada 4 ligada");
  }

  if (comando.equals("/apagar_quarto_1")){
    digitalWrite(LAMP_QUART1, LOW);
    Serial.print("\nLampada 4 desligada");
  }

      //Ligar e desligar LAMP_QUART2
  if (comando.equals("/acender_quarto_2")) {
    digitalWrite(LAMP_QUART2, HIGH);
    Serial.print("\nLampada 4 ligada");
  }

  if (comando.equals("/apagar_quarto_2")){
    digitalWrite(LAMP_QUART2, LOW);
    Serial.print("\nLampada 4 desligada");
  }
        //Ligar e desligar LAMP_BANH2
  if (comando.equals("/acender_banheiro_2")) {
    digitalWrite(LAMP_BANH2, HIGH);
    Serial.print("\nLampada 4 ligada");
  }

  if (comando.equals("/apagar_banheiro_2")){
    digitalWrite(LAMP_BANH2, LOW);
    Serial.print("\nLampada 4 desligada");
  }

/*
  // Aumenta velocidade
  if (comando.equals("/ligarvent1")){
    for(int dutyCycle = 0; dutyCycle <= 85; dutyCycle++){   
      // changing the LED brightness with PWM
      ledcWrite(ledChannel, dutyCycle);
      delay(15);
    }
  }
  // Aumenta velocidade
  if (comando.equals("/ligarvent2")){
    for(int dutyCycle = 0; dutyCycle <= 170; dutyCycle++){   
      // changing the LED brightness with PWM
      ledcWrite(ledChannel, dutyCycle);
      delay(15);
    }
  }  // Aumenta velocidade
  if (comando.equals("/ligarvent3")){
    for(int dutyCycle = 0; dutyCycle <= 255; dutyCycle++){   
      // changing the LED brightness with PWM
      ledcWrite(ledChannel, dutyCycle);
      delay(15);
    }
  }

  // Desliga
  if (comando.equals("/desligarvent")){
    for(int dutyCycle = 255; dutyCycle >= 0; dutyCycle--){
      // changing the LED brightness with PWM
      ledcWrite(ledChannel, dutyCycle);   
      delay(15);
    }
  }
*/
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