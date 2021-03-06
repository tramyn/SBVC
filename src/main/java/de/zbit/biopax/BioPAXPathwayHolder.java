/*
 * $Id: BioPAXPathwayHolder.java 180 2014-01-09 22:21:09Z draeger $
 * $URL: https://rarepos.cs.uni-tuebingen.de/svn-path/SBVC/trunk/src/de/zbit/biopax/BioPAXPathwayHolder.java $
 * ---------------------------------------------------------------------
 * This file is part of SBVC, the systems biology visualizer and
 * converter. This tools is able to read a plethora of systems biology
 * file formats and convert them to an internal data structure.
 * These files can then be visualized, either using a simple graph
 * (KEGG-style) or using the SBGN-PD layout and rendering constraints.
 * Some currently supported IO formats are SBML (+qual, +layout), KGML,
 * BioPAX, SBGN, etc. Please visit the project homepage at
 * <http://www.cogsys.cs.uni-tuebingen.de/software/SBVC> to obtain the
 * latest version of SBVC.
 *
 * Copyright (C) 2012-2014 by the University of Tuebingen, Germany.
 *
 * SBVC is free software; you can redistribute it and/or 
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation. A copy of the license
 * agreement is provided in the file named "LICENSE.txt" included with
 * this software distribution and also available online as
 * <http://www.gnu.org/licenses/lgpl-3.0-standalone.html>.
 * ---------------------------------------------------------------------
 */
package de.zbit.biopax;


import java.util.HashSet;
import java.util.Set;

import org.biopax.paxtools.model.BioPAXElement;
import org.biopax.paxtools.model.level3.Entity;

/**
 * This class stores one BioPax pathway, its BioCarta id, the standard name, 
 * and the contained entities and gene ids in {@link Set} objects 
 * 
 * @author Finja B&uuml;chel
 * @version $Rev: 180 $
 */
public class BioPAXPathwayHolder {

  Set<BioPAXElement> entities = null;
  Set<Integer> geneIDs = null;
  String biopaxRDFid;
  String name = null;
  
  public BioPAXPathwayHolder(String id){
    this.biopaxRDFid = id;
  }
  
  public BioPAXPathwayHolder(String id, String name){
    this(id);
    setPathwayName(name);
  }
  
  private boolean setPathwayName(String name) {
    if(name != null && !name.isEmpty()){
      this.name = name;
      return true;
    }
    return false;
  }

  public boolean addEntity(Entity ent){
    if(entitiesIsSet()){
      if (!entities.contains(ent)) {
        entities.add(ent);
        return true;
      }
    }
    return false;
  }

  private boolean entitiesIsSet() {
   if(entities == null)
    return createEntities();
   else
     return true;
  }

  private boolean createEntities() {
    entities = new HashSet<BioPAXElement>();
    return true;
  }
  
  public String getPathwayName(){
    return name;
  }

  public int getNoOfEntities() {
    return entitiesIsSet()? entities.size() : 0;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof BioPAXPathwayHolder){
      if(((BioPAXPathwayHolder) obj).getRDFid().equals(this.getRDFid())){
        return true;
      }
      else 
        return false;
    } else 
      return false;
  }
  
  @Override
  public int hashCode() {   
    return getRDFid().hashCode()*13;
  }

  public boolean addGeneID(Integer key) {
    if(geneIDsIsSet()){
      return geneIDs.add(key);
    }
    return false;
  }

  private boolean geneIDsIsSet() {
    if(geneIDs == null)
      return createGeneIDs();
     else
       return true;
  }

  private boolean createGeneIDs() {
    geneIDs = new HashSet<Integer>();
    return true;
  }

  public Set<Integer> getGeneIDs() {
    return geneIDs;
  }
  
  /**
   * 
   * @return an empty String if the name is null
   */
  public String getName(){
    return name==null ? "" : name;
  }

  public String getRDFid() {    
    return biopaxRDFid;
  }
}
