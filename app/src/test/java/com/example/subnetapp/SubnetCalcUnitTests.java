package com.example.subnetapp;

import com.example.subnetapp.models.SubnetCalculator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class SubnetCalcUnitTests {
  private SubnetCalculator subnetCalc = new SubnetCalculator();

  @Test
  public void subnetCalculator_IpFormatToBinary_Test(){
    String example = "1.2.3.4";
    String result = subnetCalc.ipFormatToBinary(example);
    assertEquals("00000001000000100000001100000100", result);
  }

  @Test
  public void subnetCalculator_IpBinaryToFormatted_Test(){
    String example = "00000001000000100000001100000100"; //1.2.3.4
    String result = subnetCalc.ipBinaryToFormat(example);
    assertEquals("1.2.3.4", result);
  }

  @Test
  public void subnetCalculator_Trim_FormattedIpString_Test() {
    String example = "123.123.123.123";
    int cidr = 8;
    String binary = subnetCalc.ipFormatToBinary(example);
    String result = subnetCalc.trimCidrIp(binary, cidr);
    String formattedResult = subnetCalc.ipBinaryToFormat(result);

    //assertThat(example).isEqualTo();
    assertEquals( "123.0.0.0", formattedResult);
  }

  @Test
  public void subnetCalculator_IpSplit_test(){
    String example = "00000001000000100000001100000100"; //1.2.3.4
    int cidr = 30;
    String result = subnetCalc.ipSplit(example, cidr);
    assertEquals("00000001000000100000001100000110", result);
  }

  @Test
  public void subnetCalculator_SubnetMask_test(){
    int cidr = 30;
    String result = subnetCalc.subnetMask(cidr);
    assertEquals("255.255.255.252", result);
  }

  @Test
  public void subnetCalculator_BroadcastAddress_test(){
    String example = "00000001000000100000001100000100"; //1.2.3.4
    int cidr = 30;
    String result = subnetCalc.broadcastAddress(example, cidr);
    assertEquals("1.2.3.7", result);
  }

  @Test
  public void subnetCalculator_NumberOfHosts_8to30_UpperBound_test(){
    int cidr = 8;
    int result = subnetCalc.numberOfHosts(cidr);
    assertEquals(16777214, result);
  }

  @Test
  public void subnetCalculator_NumberOfHosts_8to30_LowerBound_test(){
    int cidr = 30;
    int result = subnetCalc.numberOfHosts(cidr);
    assertEquals(2, result);
  }

  @Test
  public void subnetCalculator_NumberOfHosts_31_test(){
    int cidr = 31;
    int result = subnetCalc.numberOfHosts(cidr);
    assertEquals(2, result);
  }

  @Test
  public void subnetCalculator_NumberOfHosts_32_test(){
    int cidr = 32;
    int result = subnetCalc.numberOfHosts(cidr);
    assertEquals(1, result);
  }

  @Test
  public void subnetCalculator_FullRange_8to30_UpperBound_test(){
    String example = "00000001000000000000000000000000"; //1.0.0.0
    int cidr = 8;
    String result = subnetCalc.rangeOfAddresses(example, cidr);
    assertEquals("1.0.0.0 - 1.255.255.255", result);
  }

  @Test
  public void subnetCalculator_FullRange_8to30_LowerBound_test(){
    String example = "00000001000000100000001100000100"; // 1.2.3.4
    int cidr = 30;
    String result = subnetCalc.rangeOfAddresses(example, cidr);
    assertEquals("1.2.3.4 - 1.2.3.7", result);
  }

  @Test
  public void subnetCalculator_FullRange_31_test(){
    String example = "00000001000000100000001100000100"; // 1.2.3.4
    int cidr = 31;
    String result = subnetCalc.rangeOfAddresses(example, cidr);
    assertEquals("1.2.3.4 - 1.2.3.5", result);
  }

  @Test
  public void subnetCalculator_FullRange_32_test(){
    String example = "00000001000000100000001100000100"; // 1.2.3.4
    int cidr = 32;
    String result = subnetCalc.rangeOfAddresses(example, cidr);
    assertEquals("1.2.3.4", result);
  }

  @Test
  public void subnetCalculator_UsableRange_8to30_UpperBound_test(){
    String example = "00000001000000000000000000000000"; // 1.0.0.0
    int cidr = 8;
    String result = subnetCalc.usableIpAddresses(example, cidr);
    assertEquals("1.0.0.1 - 1.255.255.254", result);
  }

  @Test
  public void subnetCalculator_UsableRange_8to30_LowerBound_test(){
    String example = "00000001000000100000001100000100"; // 1.2.3.4
    int cidr = 30;
    String result = subnetCalc.usableIpAddresses(example, cidr);
    assertEquals("1.2.3.5 - 1.2.3.6", result);
  }

  @Test
  public void subnetCalculator_UsableRange_31_test(){
    String example = "00000001000000100000001100000100"; // 1.2.3.4
    int cidr = 31;
    String result = subnetCalc.usableIpAddresses(example, cidr);
    assertEquals("1.2.3.4 - 1.2.3.5", result);
  }

  @Test
  public void subnetCalculator_UsableRange_32_test(){
    String example = "00000001000000100000001100000100"; // 1.2.3.4
    int cidr = 32;
    String result = subnetCalc.usableIpAddresses(example, cidr);
    assertEquals("1.2.3.4", result);
  }
}
