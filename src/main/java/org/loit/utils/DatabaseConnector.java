package org.loit.utils;

import config.factory.ApiConfigFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class DatabaseConnector {
    public String getRegistrationOtp(String mobileNo) throws Exception {
//        Connection connection = DriverManager.getConnection(ApiConfigFactory.getDBConfig().DBConnectionUrl(), dbProperties());
        Connection connection = DriverManager.getConnection(ApiConfigFactory.getDBConfig().DBConnectionUrlAlt(), dbProperties());

        // Verify the OTP for the given mobile number
        String query = "SELECT otp FROM user_registration_otp WHERE mobile_no = ? ORDER BY created_date DESC LIMIT 1";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, mobileNo);

        ResultSet resultSet = preparedStatement.executeQuery();
        String otp = null;
        while (resultSet.next()) {
            otp = resultSet.getString("otp");
        }

        return otp;

    }

    public String getCustomerUsername(String mobileNo) throws Exception {
        Connection connection = DriverManager.getConnection(ApiConfigFactory.getDBConfig().DBConnectionUrl(), dbProperties());
        String query = "SELECT username FROM user_credentials, customer WHERE user_credentials.id = customer.user_credentials_id AND customer.phone= ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, mobileNo);
        ResultSet resultSet = preparedStatement.executeQuery();
        String username = null;
        while (resultSet.next()) {
            username = resultSet.getString("username");

        }
        return username;
    }


    public String getCustomerId(String randomMobileNumber) throws Exception {
        Connection connection = DriverManager.getConnection(ApiConfigFactory.getDBConfig().DBConnectionUrlAlt(), dbProperties());
        String query = "SELECT id FROM customer WHERE phone = ? ORDER BY created_date DESC LIMIT 1";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, randomMobileNumber);
        ResultSet resultSet = preparedStatement.executeQuery();
        String customerId = null;
        while (resultSet.next()) {
            customerId = resultSet.getString("id");
        }
        return customerId;
    }


    public Properties dbProperties() {
        Properties dbProps = new Properties();
        dbProps.setProperty("user", ApiConfigFactory.getDBConfig().DBUser());
        dbProps.setProperty("password", ApiConfigFactory.getDBConfig().DBPassword());
//        dbProps.setProperty("useSSL",ApiConfigFactory.getDBConfig().DBUseSSL());
//        dbProps.setProperty("requireSSL",ApiConfigFactory.getDBConfig().DBRequireSSL());
//        dbProps.setProperty("clientCertificateKeyStoreUrl",ApiConfigFactory.getDBConfig().DBClientCertificateKeyStoreUrl());
//        dbProps.setProperty("clientCertificateKeyStorePassword",ApiConfigFactory.getDBConfig().DBClientCertificateKeyStorePassword());
//        dbProps.setProperty("trustCertificateKeyStoreUrl",ApiConfigFactory.getDBConfig().DBTrustCertificateKeyStoreUrl());
        return dbProps;
    }

    public String getPublicKey() throws Exception {
        Connection connection = DriverManager.getConnection(ApiConfigFactory.getDBConfig().DBConnectionUrlAlt(), dbProperties());

        // Verify the OTP for the given mobile number
        String query = "SELECT public_key from device WHERE device_id = '5fec0aaf1fc65d8c4a6fe7b4fc943aae'";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        ResultSet resultSet = preparedStatement.executeQuery();
        String key = null;
        while (resultSet.next()) {
            key = resultSet.getString("public_key");
        }

        return key;

    }
}


