#!/bin/bash
set -e

rm client*
rm server*

# SERVER CA

SERVER_CA_KEY_PASS=servercakeypass123
SERVER_CA_STORE_PASS=servercastorepass123
SERVER_CA_VALIDITY=365000

keytool -storetype pkcs12 -keystore server-ca-keystore.p12 -storepass $SERVER_CA_STORE_PASS -alias server-ca -genkey -keyalg "RSA" -keysize 2048 -dname "CN=ActiveMQ Artemis Server Certification Authority, OU=Artemis, O=ActiveMQ" -validity $SERVER_CA_VALIDITY -ext bc:c=ca:true
keytool -storetype pkcs12 -keystore server-ca-keystore.p12 -storepass $SERVER_CA_STORE_PASS -alias server-ca -exportcert -rfc > server-ca.crt

# SERVER

SERVER_KEY_PASS=serverkeypass123
SERVER_STORE_PASS=serverstorepass123
SERVER_VALIDITY=36500

keytool -keystore server-ca-truststore.p12 -storepass $SERVER_STORE_PASS -importcert -alias server-ca -file server-ca.crt -noprompt
keytool -keystore server-keystore.jks -storepass $SERVER_STORE_PASS -alias server -genkey -keyalg "RSA" -keysize 2048 -dname "CN=ActiveMQ Artemis Server, OU=Artemis, O=ActiveMQ, L=AMQ, S=AMQ, C=AMQ" -validity $SERVER_VALIDITY -ext bc=ca:false -ext eku=sA -ext san=dns:localhost,ip:127.0.0.1
keytool -keystore server-keystore.jks -storepass $SERVER_STORE_PASS -alias server -certreq -file server.csr
keytool -keystore server-ca-keystore.p12 -storepass $SERVER_CA_STORE_PASS -alias server-ca -gencert -rfc -infile server.csr -outfile server.crt -validity $SERVER_VALIDITY -ext bc=ca:false -ext san=dns:localhost,ip:127.0.0.1
keytool -keystore server-keystore.jks -storepass $SERVER_STORE_PASS -importcert -alias server-ca -file server-ca.crt -noprompt
keytool -keystore server-keystore.jks -storepass $SERVER_STORE_PASS -importcert -alias server -file server.crt

# CLIENT CA

CLIENT_CA_KEY_PASS=clientcakeypass123
CLIENT_CA_STORE_PASS=clientcastorepass123
CLIENT_CA_VALIDITY=365000

keytool -keystore client-ca-keystore.jks -storepass $CLIENT_CA_STORE_PASS -alias client-ca -genkey -keyalg "RSA" -keysize 2048 -dname "CN=ActiveMQ Artemis Client Certification Authority, OU=Artemis, O=ActiveMQ" -validity $CLIENT_CA_VALIDITY -ext bc:c=ca:true
keytool -keystore client-ca-keystore.jks -storepass $CLIENT_CA_STORE_PASS -alias client-ca -exportcert -rfc > client-ca.crt

# CLIENT

CLIENT_KEY_PASS=clientkeypass123
CLIENT_STORE_PASS=clientstorepasss123
CLIENT_VALIDITY=36500

keytool -storetype pkcs12 -keystore client-ca-keystore.p12 -storepass $CLIENT_STORE_PASS -alias client-ca -genkey -keyalg "RSA" -keysize 2048 -dname "CN=ActiveMQ Artemis Client Certification Authority, OU=Artemis, O=ActiveMQ" -validity $CLIENT_VALIDITY -ext bc:c=ca:true
keytool -storetype pkcs12 -keystore client-ca-keystore.p12 -storepass $CLIENT_STORE_PASS -alias client-ca -exportcert -rfc > client-ca.crt
keytool -keystore client-keystore.jks -storepass $CLIENT_STORE_PASS -alias client -genkey -keyalg "RSA" -keysize 2048 -dname "CN=ActiveMQ Artemis Client, OU=Artemis, O=ActiveMQ, L=AMQ, S=AMQ, C=AMQ" -validity $CLIENT_VALIDITY -ext bc=ca:false -ext eku=cA -ext san=dns:localhost,ip:127.0.0.1
keytool -keystore client-keystore.jks -storepass $CLIENT_STORE_PASS -alias client -certreq -file client.csr
keytool -keystore client-ca-keystore.p12 -storepass $CLIENT_STORE_PASS -alias client-ca -gencert -rfc -infile client.csr -outfile client.crt -validity $CLIENT_VALIDITY -ext bc=ca:false -ext eku=cA -ext san=dns:localhost,ip:127.0.0.1
keytool -keystore client-keystore.jks -storepass $CLIENT_STORE_PASS -importcert -alias client-ca -file client-ca.crt -noprompt
keytool -keystore client-keystore.jks -storepass $CLIENT_STORE_PASS -importcert -alias client -file client.crt