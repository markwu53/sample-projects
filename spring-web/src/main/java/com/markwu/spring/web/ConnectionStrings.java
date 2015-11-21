package com.markwu.spring.web;

public class ConnectionStrings {

        public static final String ORACLE_FIOPI = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=ehplordb5141.nwie.net)(PORT=1521)))(CONNECT_DATA=(UR=A)(SERVICE_NAME=fiopi.nsc.net)))";
        public static final String ORACLE_FIOPS = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=ehplordb5141.nwie.net)(PORT=1521)))(CONNECT_DATA=(UR=A)(SERVICE_NAME=fiops.nsc.net)))";
        public static final String ORACLE_FIOPX = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=nfin01racx.nwie.net)(PORT=1521)))(CONNECT_DATA=(SERVICE_NAME=fiopx.nsc.net)))";
        public static final String MYSQL_SANDBOX = "jdbc:mysql://sandbox.hortonworks.com:3306/information_schema";
        public static final String MYSQL_WUFAMILY = "jdbc:mysql://192.168.1.106:3306/information_schema";
        public static final String PHOENIX = "jdbc:phoenix:localhost:2181:/hbase-unsecure";

}
