/*
 * CDDL HEADER START
 *
 * The contents of this file are subject to the terms of the
 * Common Development and Distribution License, Version 1.0 only
 * (the "License").  You may not use this file except in compliance
 * with the License.
 *
 * You can obtain a copy of the license at legal-notices/CDDLv1_0.txt
 * or http://forgerock.org/license/CDDLv1.0.html.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL HEADER in each
 * file and include the License file at legal-notices/CDDLv1_0.txt.
 * If applicable, add the following below this CDDL HEADER, with the
 * fields enclosed by brackets "[]" replaced with your own identifying
 * information:
 *      Portions Copyright [yyyy] [name of copyright owner]
 *
 * CDDL HEADER END
 *
 *
 *      Copyright 2006-2010 Sun Microsystems, Inc.
 *      Portions Copyright 2014-2015 ForgeRock AS
 */
package org.opends.server.monitors;

import java.util.ArrayList;
import java.util.List;

import org.forgerock.opendj.config.server.ConfigException;
import org.opends.server.admin.std.server.VersionMonitorProviderCfg;
import org.opends.server.api.MonitorProvider;
import org.opends.server.types.Attribute;
import org.opends.server.types.Attributes;
import org.opends.server.types.InitializationException;
import org.opends.server.util.DynamicConstants;

/** This class defines a monitor provider that reports Directory Server version information. */
public class VersionMonitorProvider
       extends MonitorProvider<VersionMonitorProviderCfg>
{
  /** The name of the attribute used to provide the product name. */
  public static final String ATTR_PRODUCT_NAME = "productName";
  /** The name of the attribute used to provide the short name. */
  public static final String ATTR_SHORT_NAME = "shortName";
  /** The name of the attribute used to provide the major version number. */
  public static final String ATTR_MAJOR_VERSION = "majorVersion";
  /** The name of the attribute used to provide the minor version number. */
  public static final String ATTR_MINOR_VERSION = "minorVersion";
  /** The name of the attribute used to provide the point version number. */
  public static final String ATTR_POINT_VERSION = "pointVersion";
  /** The name of the attribute used to provide the version qualifier string. */
  public static final String ATTR_VERSION_QUALIFIER = "versionQualifier";
  /** The name of the attribute used to provide the weekly build number. */
  public static final String ATTR_BUILD_NUMBER = "buildNumber";
  /** The name of the attribute used to provide the list of bugfix IDs. */
  public static final String ATTR_FIX_IDS = "fixIDs";
  /** The name of the attribute used to provide the Subversion revision number. */
  public static final String ATTR_REVISION_NUMBER = "revisionNumber";
  /** The name of the attribute used to provide the build ID (aka the build timestamp). */
  public static final String ATTR_BUILD_ID = "buildID";
  /** The name of the attribute used to provide the compact version string. */
  public static final String ATTR_COMPACT_VERSION = "compactVersion";
  /** The name of the attribute used to provide the full version string. */
  public static final String ATTR_FULL_VERSION = "fullVersion";

  @Override
  public void initializeMonitorProvider(VersionMonitorProviderCfg configuration)
         throws ConfigException, InitializationException
  {
    // No initialization is required.
  }

  @Override
  public String getMonitorInstanceName()
  {
    return "Version";
  }

  @Override
  public List<Attribute> getMonitorData()
  {
    ArrayList<Attribute> attrs = new ArrayList<>(12);

    attrs.add(createAttribute(ATTR_PRODUCT_NAME, DynamicConstants.PRODUCT_NAME));
    attrs.add(createAttribute(ATTR_SHORT_NAME, DynamicConstants.SHORT_NAME));
    attrs.add(createAttribute(ATTR_MAJOR_VERSION, DynamicConstants.MAJOR_VERSION));
    attrs.add(createAttribute(ATTR_MINOR_VERSION, DynamicConstants.MINOR_VERSION));
    attrs.add(createAttribute(ATTR_POINT_VERSION, DynamicConstants.POINT_VERSION));

    String versionQualifier = DynamicConstants.VERSION_QUALIFIER;
    if (versionQualifier != null && versionQualifier.length() > 0)
    {
      attrs.add(createAttribute(ATTR_VERSION_QUALIFIER, versionQualifier));
    }

    int buildNumber = DynamicConstants.BUILD_NUMBER;
    if (buildNumber > 0)
    {
      attrs.add(createAttribute(ATTR_BUILD_NUMBER, buildNumber));
    }

    String fixIDs = DynamicConstants.FIX_IDS;
    if (fixIDs != null && fixIDs.length() > 0)
    {
      attrs.add(createAttribute(ATTR_FIX_IDS, fixIDs));
    }

    attrs.add(createAttribute(ATTR_REVISION_NUMBER, DynamicConstants.REVISION));
    attrs.add(createAttribute(ATTR_BUILD_ID, DynamicConstants.BUILD_ID));
    attrs.add(createAttribute(ATTR_COMPACT_VERSION, DynamicConstants.COMPACT_VERSION_STRING));
    attrs.add(createAttribute(ATTR_FULL_VERSION, DynamicConstants.FULL_VERSION_STRING));

    return attrs;
  }

  private Attribute createAttribute(String name, Object value)
  {
    return Attributes.create(name, String.valueOf(value));
  }
}
