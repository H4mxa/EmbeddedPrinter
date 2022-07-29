import React, { useEffect } from "react";
import { Text, NativeModules, Button, Alert } from "react-native";

const TestPrint = () => {
  const { EmbeddedPrinter } = NativeModules;
  let printRecipt = `Test Store 5
Trans No:TT2207261621298001
Date: 29-Jul-2022
Staff Name: ahsan

S#  Description      Qty  Price   Amount
 1   Dalda Cooking Oi 1    310     310    


                         Total Items:      1
                      Total Quantity:      1
                           Sub Total:    310
Cash Received:     0    
Cash Return:      0    

All prices are inclusive of taxes 
رد فعل أصلي نص إلى صورة test5@howmuch.pk
---------------------</C>`;
  const HandleNativeModules = () => {
    EmbeddedPrinter.getSmartpeakPermissions(
      `Test Store 5
    Trans No:TT2207261621298001
    Date: 29-Jul-2022
    Staff Name: ahsan
    
    S#  Description      Qty  Price   Amount
     1   Dalda Cooking Oi 1    310     310    
    
    
                             Total Items:      1
                          Total Quantity:      1
                               Sub Total:    310
    Cash Received:     0    
    Cash Return:      0    
    
    All prices are inclusive of taxes 
    رد فعل أصلي نص إلى صورة test5@howmuch.pk
    ---------------------</C>`
    ).then((response, error) => {
      Alert.alert("Response from Embedded Printer is [ " + response + " ]");
    });
  };

  return (
    <>
      <Button
        onPress={() => {
          HandleNativeModules();
        }}
        title="Text Print"
      />
    </>
  );
};

export default TestPrint;
