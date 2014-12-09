/*
 * This file is part of a Werum IT Solutions GmbH project.
 *
 * Copyright (c)
 *    Werum IT Solutions GmbH
 *    All rights reserved.
 *
 * This source file may be managed in different Java package structures,
 * depending on actual usage of the source file by the Copyright holders:
 *
 * for Werum:  com.werum.* or any other Werum owned Internet domain
 *
 * Any use of this file as part of a software system by none Copyright holders
 * is subject to license terms.
 *
 * Last Change: $Id: Eclipse V3X Code Templates.xml 126559 2014-06-19 11:28:59Z zemlin $
 *
 * $HeadURL: svn://pasxsvn.werum.net/pasx/branches/dev-3.2.0/development/templates/Eclipse%20V3X%20Code%20Templates.xml $
 */
package Gui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Description of class...
 *
 * @author simon_stratmann
 * @company Werum IT Solutions GmbH
 * @created 09.12.2014
 * @since PAS-X V3.2.0
 */
public class SimpleGraph<S, T> {

    private TreeMap<S, TreeMap<S, T>> map = new TreeMap<S, TreeMap<S, T>>();

    public T getVertexValue(S source, S target) {
        return map.get(source).get(target);
    }

    public void setVertexValue(S source, S target, T value) {
        if (!map.containsKey(source)) {
            map.put(source, new TreeMap<S, T>());
        }
        map.get(source).put(target, value);
    }

    public boolean isConnected(S source, S target) {
        if (!map.containsKey(source)) {
            return false;
        }
        if (!map.get(source).containsKey(target)) {
            return false;
        }
        return map.get(source).containsKey(target);
    }

    public Collection<S> getTargets(S source) {
        if (!map.containsKey(source)) {
            return new ArrayList<S>();
        }
        return map.get(source).keySet();
    }

    public Collection<S> getAllNodes() {
        TreeSet<S> nodes = new TreeSet<S>();
        nodes.addAll(map.keySet());
        for (S keySource : map.keySet()) {
            nodes.addAll(map.get(keySource).keySet());
        }
        return nodes;
    }

}
