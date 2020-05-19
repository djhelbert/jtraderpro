/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtraderpro;

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

  private static final ObjectMapper objectMapper = new ObjectMapper();

  private static Portfolio portfolio;

  public final static File DEFAULT_FILE = new File(System.getProperty("user.home") + File.separator + DEFAULT);

  private static PortfolioProvider provider = new PortfolioProvider();

  /**
   * Private
   */
  private PortfolioProvider() {
  }

  /**
   * Get Singleton
   *
   * @return PortfolioProvider
   */
  public static final PortfolioProvider getInstance() {
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

  public void load() throws IOException {
    load(DEFAULT_FILE);
  }

  public void load(File file) throws IOException {
    portfolio = objectMapper.readValue(file, Portfolio.class);
  }

  public void save() throws IOException {
    save(DEFAULT_FILE);
  }

  public void save(File file) throws IOException {
    objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, portfolio);
  }

  public boolean defaultExists() {
    return DEFAULT_FILE.exists();
  }

  public void delete(File file) {
    file.delete();
  }
}
