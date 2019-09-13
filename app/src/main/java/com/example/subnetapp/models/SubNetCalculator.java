package com.example.subnetapp.models;

public class SubNetCalculator {


  //For using functions in SplitterActivity
  public SubNetCalculator(){
  }

  public int ipToCidrIp(int aCidr, int ip){
    int ipInt = ip;
    int cidr = aCidr;

    //make into binary formatted string
    String binaryNum = String.format("%32s", Integer.toBinaryString(ipInt)).replace(' ', '0');

    System.out.println(binaryNum);

    StringBuilder builderBinaryNum = new StringBuilder(binaryNum);

    //change binary string substring to 0 after cidr
    for(int i = cidr; i < binaryNum.length(); i++) {
      builderBinaryNum.setCharAt(i, '0');
    }

    binaryNum = builderBinaryNum.toString();

    //change back into int
    System.out.println(binaryNum);

    return 0;
  }

  public String trimCidrIp(String ip, int cidr){

    //make into binary formatted string
    String binaryNum = ip;

    StringBuilder builderBinaryNum = new StringBuilder(binaryNum);

    //change binary string substring to 0 after cidr
    for(int i = cidr; i < binaryNum.length(); i++) {
      builderBinaryNum.setCharAt(i, '0');
    }

    binaryNum = builderBinaryNum.toString();

    return binaryNum;
  }

  public String ipFormatToBinary(String ip) {
    String[] stringArray = ip.split("\\.");
    Integer[] intArray = new Integer[4];
    for (int i = 0; i < 4; i++) {
      try {
        intArray[i] = Integer.parseInt(stringArray[i]);
      } catch (NumberFormatException nfe) {}
    }

    int decimalNum = intArray[3] + (intArray[2] * 256) + (intArray[1] * 65536) + (intArray[0] * 16777216);
    String binaryNum = String.format("%32s", Integer.toBinaryString(decimalNum)).replace(' ', '0');

    return binaryNum;
  }

  public String ipBinaryToFormat(String ipBinary){

    String[] arr = new String[4];

    for(int i = 0; i < arr.length; i++) {
      String temp = ipBinary.substring(i*8, (i*8)+8);
      int number = Integer.parseInt(temp, 2);
      String ipInt = Integer.toString(number);
      arr[i] = ipInt;
    }

    String ipFormat = arr[0] + "." + arr[1] + "." + arr[2] + "." + arr[3];

    return ipFormat;
  }

  public String ipSplit(String ipBinary, int cidr) {
    //assuming check that cidr is not 32
    char[] splitOne = ipBinary.toCharArray();
    splitOne[cidr] = '1';
    String value = new String(splitOne);
    return value;
  }

  public String subnetMask(int cidr) {

    char[] arr = new char[32];
    for(int i = 0; i < arr.length; i++){
      if (i < cidr) {
        arr[i] = '1';
      } else {
        arr[i] = '0';
      }
    }
    String theString = new String(arr);
    theString = ipBinaryToFormat(theString);

    return theString;
  }

  public int numberOfHosts(int cidr) {
    int num = 32 - cidr;

    double allHosts = Math.pow(2, num);
    int usableHosts = (int) allHosts;

    if(cidr >= 31) {
      return usableHosts;
    } else {
      return usableHosts - 2;
    }
  }

  public String rangeOfAddresses(String ipBinary, int cidr) {
    String start = ipBinaryToFormat(ipBinary);
    if(cidr == 32){
      return start;
    }

    int num = 32 - cidr;
    int allHosts = (int) Math.pow(2, num) - 1;

    long startInt = Long.parseLong(ipBinary,2);
    long endInt = startInt + allHosts;
    String endBin = String.format("%32s", Integer.toBinaryString((int) endInt)).replace(' ', '0');
    String end = ipBinaryToFormat(endBin);

    return start + " - " + end;
  }

  public String usableIpAddresses(String ipBinary, int cidr) {
    if(cidr == 32){
      ipBinaryToFormat(ipBinary);
      return ipBinaryToFormat(ipBinary);
    }

    int num = 32 - cidr;
    int allHosts = (int) Math.pow(2, num) -1;

    long startInt = Long.parseLong(ipBinary,2);
    long endInt = startInt + allHosts;

    if(cidr < 31) {
      startInt++;
      endInt--;
    }

    String startBin = String.format("%32s", Integer.toBinaryString((int) startInt)).replace(' ', '0');
    String start = ipBinaryToFormat(startBin);
    String endBin = String.format("%32s", Integer.toBinaryString((int) endInt)).replace(' ', '0');
    String end = ipBinaryToFormat(endBin);

    return start + " - " + end;
  }

  public String broadcastAddress(String ipBinary, int cidr){
    if(cidr == 32){
      ipBinaryToFormat(ipBinary);
      return ipBinaryToFormat(ipBinary);
    }

    int num = 32 - cidr;
    int allHosts = (int) Math.pow(2, num) -1;


    long startInt = Long.parseLong(ipBinary,2);
    long endInt = startInt + allHosts;
    String endBin = String.format("%32s", Integer.toBinaryString((int) endInt)).replace(' ', '0');
    String end = ipBinaryToFormat(endBin);
    return end;
  }

}
