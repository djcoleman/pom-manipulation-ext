/*******************************************************************************
 * Copyright (c) 2014 Red Hat, Inc..
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     Red Hat, Inc. - initial API and implementation
 ******************************************************************************/
package org.commonjava.maven.ext.manip.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.maven.model.Model;
import org.apache.maven.model.Parent;
import org.apache.maven.project.MavenProject;
import org.commonjava.maven.ext.manip.model.Project;

/**
 * Convenience utilities for converting {@link Model} and {@link MavenProject} instances to GA / GAV strings.
 *
 * @author jdcasey
 */
public final class IdUtils
{
    /**
     * Regex pattern for parsing a Maven GAV
     */
    public static final Pattern gavPattern = Pattern.compile( "\\s*([\\w\\-_.]+):([\\w\\-_.]+):(\\d[\\w\\-_.]+)\\s*" );

    private IdUtils()
    {
    }

    public static boolean validGav( String gav )
    {
        Matcher matcher = gavPattern.matcher( gav );
        return matcher.matches();
    }

    public static String gav( final MavenProject project )
    {
        return String.format( "%s:%s:%s", project.getGroupId(), project.getArtifactId(), project.getVersion() );
    }

    public static String gav( final Project project )
    {
        return String.format( "%s:%s:%s", project.getGroupId(), project.getArtifactId(), project.getVersion() );
    }

    public static String gav( final Model model )
    {
        String g = model.getGroupId();
        String v = model.getVersion();

        final Parent p = model.getParent();
        if ( p != null )
        {
            if ( g == null )
            {
                g = p.getGroupId();
            }

            if ( v == null )
            {
                v = p.getVersion();
            }
        }

        return String.format( "%s:%s:%s", g, model.getArtifactId(), v );
    }

    public static String ga( final Model model )
    {
        String g = model.getGroupId();

        final Parent p = model.getParent();
        if ( p != null )
        {
            if ( g == null )
            {
                g = p.getGroupId();
            }
        }

        return ga( g, model.getArtifactId() );
    }

    public static String ga( final MavenProject project )
    {
        return ga( project.getGroupId(), project.getArtifactId() );
    }

    public static String ga( final Project project )
    {
        return ga( project.getGroupId(), project.getArtifactId() );
    }

    public static String ga( final Parent project )
    {
        return ga( project.getGroupId(), project.getArtifactId() );
    }

    public static String ga( final String g, final String a )
    {
        return String.format( "%s:%s", g, a );
    }

    public static String gav( final String g, final String a, final String v )
    {
        return String.format( "%s:%s:%s", g, a, v );
    }
}
