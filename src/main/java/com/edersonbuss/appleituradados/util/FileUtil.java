/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edersonbuss.appleituradados.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ederson
 */
public class FileUtil {

    private final static Logger LOGGER = Logger.getLogger(FileUtil.class.getName());

    public static void criarDiretorios() {
        try {
            if (!Paths.get(Constants.PATH_DIRETORIO_IN).toFile().exists()) {
                Files.createDirectories(Paths.get(Constants.PATH_DIRETORIO_IN));
            }
            if (!Paths.get(Constants.PATH_DIRETORIO_OUT).toFile().exists()) {
                Files.createDirectories(Paths.get(Constants.PATH_DIRETORIO_OUT));
            }
        } catch (final IOException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }

    }

}
