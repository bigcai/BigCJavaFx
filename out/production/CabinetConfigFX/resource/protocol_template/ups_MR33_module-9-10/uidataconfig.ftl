<map>
    <entry>
        <string>pdu_list</string>
        <UIInfo>
            <name>pdu_list</name>
            <propertyDefines class="linked-list">
                <ArrayPropertyDefine>
                    <name>pdus</name>
                    <propertyDefines>
                        <ComposePropertyDefine>
                            <name>${name}</name>
                            <propertyDefines>
                                <ConstantProperty>
                                    <name>name</name>
                                    <value>${name}</value>
                                </ConstantProperty>
                                <SimplePropertyDefine>
                                    <name>voltage</name>
                                    <sensorEntry>
                                        <deviceId>${deviceId}</deviceId>
                                        <sensorId>PDU.Voltage</sensorId>
                                    </sensorEntry>
                                </SimplePropertyDefine>
                                <SimplePropertyDefine>
                                    <name>current</name>
                                    <sensorEntry>
                                        <deviceId>${deviceId}</deviceId>
                                        <sensorId>PDU.Current</sensorId>
                                    </sensorEntry>
                                </SimplePropertyDefine>
                            </propertyDefines>
                        </ComposePropertyDefine>
                    </propertyDefines>
                </ArrayPropertyDefine>
            </propertyDefines>
        </UIInfo>
    </entry>
</map>