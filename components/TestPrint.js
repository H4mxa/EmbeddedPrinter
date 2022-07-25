import React, { useEffect } from "react";
import { Text, NativeModules, Button, Alert } from "react-native";

const TestPrint = () => {
  const { EmbeddedPrinter } = NativeModules;

  const HandleNativeModules = () => {
    EmbeddedPrinter.getSmartpeakPermissions().then((response, error) => {
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
