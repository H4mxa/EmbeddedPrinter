import React, { useEffect } from "react";
import { Text, NativeModules, Button } from "react-native";

const TestPrint = () => {
  const { EmbeddedPrinter } = NativeModules;

  const HandleNativeModules = () => {
    EmbeddedPrinter.initPrinter().then((response, error) => {
      console.log("init printer: ", response, ": => ", error);
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
