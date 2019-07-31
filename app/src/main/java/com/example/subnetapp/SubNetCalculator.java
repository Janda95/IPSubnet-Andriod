package com.example.subnetapp;

public class SubNetCalculator {

  private String rootSubnetString;
  private int rootSubnetBinary;
  private int cidr;
  private int ipInt;

  public SubNetCalculator(String ip, String cidr) {

    try {
      this.cidr = Integer.parseInt(cidr);
    } catch (NumberFormatException nfe) {}
    rootSubnetString = ip;
    ipInt = stringToBinary(ip);
  }

  public int stringToBinary(String ip){
    String[] stringArray = ip.split("\\.");
    Integer[] intArray = new Integer[4];
    for (int i = 0; i < 4; i++) {
      try {
        intArray[i] = Integer.parseInt(stringArray[i]);
      } catch (NumberFormatException nfe) {}
    }

    int decimalNum = intArray[3] + (intArray[2] * 256) + (intArray[1] * 65536) + (intArray[0] * 16777216);

    return decimalNum;
  }

  public String binaryToString(){

    return "";
  }

  public int getBinaryNumber() {
    return ipInt;
  }


}
