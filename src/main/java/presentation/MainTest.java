/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import data.DBConnector;
import data.DataMapper;
import java.sql.Connection;

/**
 *
 * @author thomasthimothee
 */
public class MainTest {
        public static void main(String[]args) throws Exception
        {
            DataMapper dm = new DataMapper();
            dm.customerSignup("Marie", "mariemail", "0004-4424", "marieuser", "mariepass");
            
		}
            
}
