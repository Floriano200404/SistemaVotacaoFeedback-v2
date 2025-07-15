/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.util;

import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Athos
 */
public class ConfigLoaderUtil {
    public static Properties getProperties() throws Exception {
        Properties props = new Properties();
        try (InputStream input = ConfigLoaderUtil.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Desculpe, não foi possível encontrar o arquivo config.properties");
                return null;
            }
            props.load(input);
        }
        return props;
    }
}
