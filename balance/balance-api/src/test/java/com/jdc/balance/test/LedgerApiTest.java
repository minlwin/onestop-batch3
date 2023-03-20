package com.jdc.balance.test;

import org.junit.jupiter.api.ClassOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestClassOrder;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages("com.jdc.balance.test.ledger")
@TestClassOrder(OrderAnnotation.class)
public class LedgerApiTest {

}
