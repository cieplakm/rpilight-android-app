package com.paragrafxv.workhours.admin.server;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        waitForConnection();
    }

//
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.net.ServerSocket;
//import java.net.Socket;
//
////RASPBERRYPI
//import com.pi4j.io.gpio.GpioController;
//import com.pi4j.io.gpio.GpioFactory;
//import com.pi4j.io.gpio.GpioPinDigitalOutput;
//import com.pi4j.io.gpio.PinState;
//import com.pi4j.io.gpio.RaspiPin;
//
//public class Server {
//	//SERVER
	public ServerSocket socketServer;
	public Socket clientSocket;
	public DataOutputStream outStream;
	public DataInputStream inStream;
//
//
//	//RASP
//	final GpioController gpio;
//	final GpioPinDigitalOutput pin;
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		Server instanceOfServer = new Server();
//
//		while(true){
//			instanceOfServer.waitForConnection();
//		}
//	}
//
//	public Server(){
//
//
//
//
//		//RASPPERRYPI
//
//		// create gpio controller
//        gpio = GpioFactory.getInstance();
//
//        // provision gpio pin #01 as an output pin and turn on
//        pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "MyLED", PinState.HIGH);
//
//        // set shutdown state for this pin
//        pin.setShutdownOptions(true, PinState.LOW);
//
//
//
//	}
//
	public void waitForConnection(){
		try{
			System.out.println("Server na portcie 8880");
			socketServer = new ServerSocket(8880);
		}catch(IOException e){
			e.printStackTrace();
		}



			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						System.out.println("Czekam na klijenta na porcie 8880");
						clientSocket = socketServer.accept();
					} catch (IOException e) {
						e.printStackTrace();
					}

					if(clientSocket != null){
						System.out.println("Zaakceptowano clienta, pomyslnie z " + clientSocket.getInetAddress());

						openInputStream();
						openOutputStream();
						//readInputData();

						while(true){
							writeOutputData(true);
							try {
								Thread.sleep(5000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}



					}
				}
			}).start();


	}

	public void openOutputStream(){
		try {
			System.out.println("Otwietam stream wyjsciowy");
			outStream=new DataOutputStream(clientSocket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void openInputStream(){
		try {
			System.out.println("Otwietam stream wejsciowy");
			inStream=new DataInputStream(clientSocket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void readInputData(){
		try {
			System.out.println("Czekam na dane...");
			int boo = (int)inStream.readInt();
			System.out.println("Otrzymano dane od klijenta: opcja: " + boo);
			//choseComand(boo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}
	public void writeOutputData(Boolean b){
		try {
			System.out.println("Wysylam dane...");
			//outStream.writeUTF("Otrzymano dane");
			outStream.writeBoolean(b);
			System.out.println("Wyslano dane");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//
//	public void changeGPIOState(){
//		pin.toggle();
//		writeOutputData(getGpioState());
//	}
//
//	public void swichOnGpioPin(){
//		pin.high();
//	}
//
//	public void swichOffGpioPin(){
//		pin.low();
//	}
//
//	public Boolean getGpioState(){
//		// get explicit state enumeration for the GPIO pin associated with the button
//		Boolean ifPinIsHihg = pin.isHigh(); // high ==> true ==> wy�aczona
//		System.out.println("Lampka jest: " + ifPinIsHihg.toString());
//        return ifPinIsHihg;
//
//	}
//
//	public void choseComand(int o){
//		switch (o) {
//
//			case 1: changeGPIOState();
//				break;
//			case 2: writeOutputData(getGpioState());
//				break;
//		}
//	}
//
//}
//
//



}
