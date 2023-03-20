package com.jdc.balance.test;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import com.jdc.balance.test.ledger.LedgerApiCreateTest;
import com.jdc.balance.test.ledger.LedgerApiSearchTest;
import com.jdc.balance.test.ledger.LedgerApiUpdateTest;
import com.jdc.balance.test.ledger.LedgerApiUploadTest;

@Suite
@SelectClasses({
	LedgerApiCreateTest.class, 
	LedgerApiUpdateTest.class, 
	LedgerApiUploadTest.class, 
	LedgerApiSearchTest.class})
public class LedgerApiTest {

}
