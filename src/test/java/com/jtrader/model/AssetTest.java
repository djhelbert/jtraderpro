/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtrader.model;

import com.jtraderpro.model.Asset;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author djhelbert
 */
public class AssetTest {
  @Test
  public void testEquals() {
    Asset n = null;
    Asset T = new Asset("T", "Att Inc.", 1);
    Asset t = new Asset("t", "Att Inc.", 2);
    Asset o = new Asset("orcl", "Oracle", 3);
    Asset m = new Asset("MSFT", "Microsoft", 3);

    assertFalse(T.equals(m));
    assertFalse(T.equals(o));
    assertFalse(T.equals(n));
    assertTrue(T.equals(t));
    assertTrue(t.equals(T));
  }
}
