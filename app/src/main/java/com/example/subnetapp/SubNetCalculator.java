package com.example.subnetapp;

public class SubNetCalculator {

  private String rootSubnetString;
  private int rootSubnetBinary;
  private int cidr;
  private int ipInt;



  //For using functions in Main Activity
  public SubNetCalculator() {}

  //For using functions in SplitterActivity
  public SubNetCalculator(String ip, String cidr, int ipNum) {

    try {
      this.cidr = Integer.parseInt(cidr);
      ipInt = ipNum;
    } catch (NumberFormatException nfe) {}
    rootSubnetString = ip;
  }

  public String binaryToString(){

    return "";
  }

  public int getBinaryNumber() {
    return ipInt;
  }

  //public int trimCidrIp(String ip, String cidr){
  public String trimCidrIp(){
    int localip = ipInt;
    int localcidr = cidr;

    //make into binary formatted string
    String binaryNum = String.format("%32s", Integer.toBinaryString(ipInt)).replace(' ', '0');

    System.out.println(binaryNum);

    StringBuilder builderBinaryNum = new StringBuilder(binaryNum);

    //change binary string substring to 0 after cidr
    for(int i = localcidr; i < binaryNum.length(); i++) {
      builderBinaryNum.setCharAt(i, '0');
    }

    binaryNum = builderBinaryNum.toString();

    //change back into int
    System.out.println(binaryNum);

    return binaryNum;
  }

}
