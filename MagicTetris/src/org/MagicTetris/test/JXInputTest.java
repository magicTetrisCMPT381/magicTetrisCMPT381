//package org.MagicTetris.test;
//
//import de.hardcode.jxinput.JXInputManager;
//import de.hardcode.jxinput.directinput.DirectInputDevice;
//import de.hardcode.jxinput.event.JXInputEventManager;
//
//public class JXInputTest{
//
//        
//        
//        public static void main(String[] args){
//                
//                System.load("E:\\gitHub\\magicTetrisCMPT381\\MagicTetris\\jxinput.dll");
//                JXInputEventManager.setTriggerIntervall( 50 );
//                for(int i = 0; i < JXInputManager.getNumberOfDevices(); i++){
//                        if(JXInputManager.getJXInputDevice(i).getName().equals("Controller (XBOX 360 For Windows)")){
//                                DirectInputDevice xbox = new DirectInputDevice(i);
//                                System.out.println("This is XBOX");
//                      }
//                }
//
//        }
//
//}