/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtraderpro;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jtraderpro.model.Asset;
import com.jtraderpro.model.AssetGroup;
import com.jtraderpro.model.Portfolio;
import java.io.File;
import java.io.IOException;

/**
 * Portfolio Provider
 *
 * @author djhelbert
 */
public class PortfolioProvider {

  private static final String DEFAULT = "jtraderpro.json";
  private static final ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  private static final File DEFAULT_FILE = new File(System.getProperty("user.home") + File.separator + DEFAULT);
  private static final PortfolioProvider provider = new PortfolioProvider();

  private static Portfolio portfolio;
  
  /**
   * Private Constructor
   */
  private PortfolioProvider() {
  }

  /**
   * Get Singleton
   *
   * @return PortfolioProvider
   */
  public static PortfolioProvider getInstance() {
    return provider;
  }

  public Portfolio getPortfolio() {
    return portfolio;
  }

  public Portfolio getNewPortfolio() {
    portfolio = new Portfolio();
    portfolio.getGroups().add(new AssetGroup("Portfolio", 0));
    portfolio.getGroups().get(0).addAsset(new Asset("ORCL", "Oracle Corporation", 0));
    portfolio.getGroups().get(0).addAsset(new Asset("AAPL", "Apple Inc.", 1));

    return portfolio;
  }

  /**
   * Load
   *
   * @throws IOException IO Exception
   */
  public void load() throws IOException {
    load(DEFAULT_FILE);
  }

  /**
   * Load File
   * @param file File
   * @throws IOException IO Error
   */
  public void load(File file) throws IOException {
    portfolio = objectMapper.readValue(file, Portfolio.class);
  }

  /**
   * Save
   *
   * @throws IOException IO Error
   */
  public void save() throws IOException {
    save(DEFAULT_FILE);
  }

  /**
   * Save File
   *
   * @param file File
   * @throws IOException IO Error
   */
  public void save(File file) throws IOException {
    objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, portfolio);
  }

  /**
   * Default File Exists
   *
   * @return boolean
   */
  public boolean defaultExists() {
    return DEFAULT_FILE.exists();
  }
}
