/*******************************************************************************
 * Copyright (c) 2003, 2022 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.ibm.adapter.jdbc;

import java.io.InputStream;
import java.io.Reader;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

import com.ibm.adapter.AdapterUtil;
import com.ibm.ejs.ras.Tr;
import com.ibm.ejs.ras.TraceComponent;

/**
 * Wrapper class for CallableStatement object. <p>
 */
public class JdbcCallableStatement extends JdbcPreparedStatement implements CallableStatement {

    private static final TraceComponent tc = Tr.register(JdbcCallableStatement.class);

    /** The underlying CallableStatement object. */
    private CallableStatement cstmtImpl;

    /**
     * Create a WebSphere CallableStatement wrapper.
     * 
     * @param cstmtImplObject the JDBC CallableStatement implementation class to be wrapped.
     * @param connWrapper     the WebSphere JDBC Connection wrapper creating this statement.
     */
    JdbcCallableStatement(
                          CallableStatement cstmtImplObject,
                          JdbcConnection connWrapper) {
        if (tc.isEntryEnabled())
            Tr.entry(tc, "<init>", new Object[] {
                                                  AdapterUtil.toString(cstmtImplObject),
                                                  connWrapper });

        stmtImpl = pstmtImpl = cstmtImpl = cstmtImplObject;
        parentWrapper = connWrapper;
        childWrappers = new java.util.Vector(1);

        if (tc.isEntryEnabled())
            Tr.exit(tc, "<init>", this);
    }

    /**
     * Perform any wrapper-specific close logic. This method is called by the default
     * JdbcObject close method.
     * 
     * @return SQLException the first error to occur while closing the object.
     */
    final SQLException closeWrapper() {
        cstmtImpl = null;

        // Delegate to the Statement wrapper, not the PreparedStatement wrapper, since the
        // PreparedStatement wrapper likes to cache stuff and we don't want that.
        return closeStatementWrapper();
    }

    public Array getArray(int i) throws SQLException {
        try {
            return cstmtImpl.getArray(i);
        } catch (SQLException ex) {
            if (tc.isDebugEnabled())
                Tr.debug(tc, "getArray",
                         new Object[] {
                                        "SQL STATE:  " + ex.getSQLState(),
                                        "ERROR CODE: " + ex.getErrorCode(),
                                        ex });
            throw AdapterUtil.mapException(this, ex);
        } catch (NullPointerException nullX) {
            throw runtimeXIfNotClosed(nullX);
        }
    }

    public java.math.BigDecimal getBigDecimal(int i) throws SQLException {
        try {
            return cstmtImpl.getBigDecimal(i);
        } catch (SQLException ex) {
            if (tc.isDebugEnabled())
                Tr.debug(tc, "getBigDecimal",
                         new Object[] {
                                        "SQL STATE:  " + ex.getSQLState(),
                                        "ERROR CODE: " + ex.getErrorCode(),
                                        ex });
            throw AdapterUtil.mapException(this, ex);
        } catch (NullPointerException nullX) {
            throw runtimeXIfNotClosed(nullX);
        }
    }

    /**
     * @deprecated
     */
    @Deprecated
    @Override
    public java.math.BigDecimal getBigDecimal(int i, int scale) throws SQLException {
        /*
         * try {
         * return cstmtImpl.getBigDecimal(i, scale);
         * }
         * catch (SQLException ex) {
         * if (tc.isDebugEnabled())
         * Tr.debug(
         * tc,
         * "getBigDecimal",
         * new Object[] {
         * "SQL STATE:  " + ex.getSQLState(),
         * "ERROR CODE: " + ex.getErrorCode(),
         * ex });
         * throw ex;
         * }
         * catch (NullPointerException nullX) {
         * // No FFDC code needed; we might be closed.
         * throw runtimeXIfNotClosed(nullX);
         * }
         */
        return null;
    }

    public Blob getBlob(int i) throws SQLException {
        try {
            return cstmtImpl.getBlob(i);
        } catch (SQLException ex) {
            if (tc.isDebugEnabled())
                Tr.debug(tc, "getBlob",
                         new Object[] {
                                        "SQL STATE:  " + ex.getSQLState(),
                                        "ERROR CODE: " + ex.getErrorCode(),
                                        ex });
            throw AdapterUtil.mapException(this, ex);
        } catch (NullPointerException nullX) {
            throw runtimeXIfNotClosed(nullX);
        }
    }

    public boolean getBoolean(int i) throws SQLException {
        try {
            return cstmtImpl.getBoolean(i);
        } catch (SQLException ex) {
            if (tc.isDebugEnabled())
                Tr.debug(tc, "getBoolean",
                         new Object[] {
                                        "SQL STATE:  " + ex.getSQLState(),
                                        "ERROR CODE: " + ex.getErrorCode(),
                                        ex });
            throw AdapterUtil.mapException(this, ex);
        } catch (NullPointerException nullX) {
            throw runtimeXIfNotClosed(nullX);
        }
    }

    public byte getByte(int i) throws SQLException {
        try {
            return cstmtImpl.getByte(i);
        } catch (SQLException ex) {
            if (tc.isDebugEnabled())
                Tr.debug(tc, "getByte",
                         new Object[] {
                                        "SQL STATE:  " + ex.getSQLState(),
                                        "ERROR CODE: " + ex.getErrorCode(),
                                        ex });
            throw AdapterUtil.mapException(this, ex);
        } catch (NullPointerException nullX) {
            throw runtimeXIfNotClosed(nullX);
        }
    }

    public byte[] getBytes(int i) throws SQLException {
        try {
            return cstmtImpl.getBytes(i);
        } catch (SQLException ex) {
            if (tc.isDebugEnabled())
                Tr.debug(tc, "getBytes",
                         new Object[] {
                                        "SQL STATE:  " + ex.getSQLState(),
                                        "ERROR CODE: " + ex.getErrorCode(),
                                        ex });
            throw AdapterUtil.mapException(this, ex);
        } catch (NullPointerException nullX) {
            throw runtimeXIfNotClosed(nullX);
        }
    }

    public Clob getClob(int i) throws SQLException {
        try {
            return cstmtImpl.getClob(i);
        } catch (SQLException ex) {
            if (tc.isDebugEnabled())
                Tr.debug(tc, "getClob",
                         new Object[] {
                                        "SQL STATE:  " + ex.getSQLState(),
                                        "ERROR CODE: " + ex.getErrorCode(),
                                        ex });
            throw AdapterUtil.mapException(this, ex);
        } catch (NullPointerException nullX) {
            throw runtimeXIfNotClosed(nullX);
        }
    }

    public Date getDate(int i) throws SQLException {
        try {
            return cstmtImpl.getDate(i);
        } catch (SQLException ex) {
            if (tc.isDebugEnabled())
                Tr.debug(tc, "getDate",
                         new Object[] {
                                        "SQL STATE:  " + ex.getSQLState(),
                                        "ERROR CODE: " + ex.getErrorCode(),
                                        ex });
            throw AdapterUtil.mapException(this, ex);
        } catch (NullPointerException nullX) {
            throw runtimeXIfNotClosed(nullX);
        }
    }

    public Date getDate(int i, Calendar cal) throws SQLException {
        try {
            return cstmtImpl.getDate(i, cal);
        } catch (SQLException ex) {
            if (tc.isDebugEnabled())
                Tr.debug(tc, "getDate",
                         new Object[] {
                                        "SQL STATE:  " + ex.getSQLState(),
                                        "ERROR CODE: " + ex.getErrorCode(),
                                        ex });
            throw AdapterUtil.mapException(this, ex);
        } catch (NullPointerException nullX) {
            throw runtimeXIfNotClosed(nullX);
        }
    }

    public double getDouble(int i) throws SQLException {
        try {
            return cstmtImpl.getDouble(i);
        } catch (SQLException ex) {
            if (tc.isDebugEnabled())
                Tr.debug(tc, "getDouble",
                         new Object[] {
                                        "SQL STATE:  " + ex.getSQLState(),
                                        "ERROR CODE: " + ex.getErrorCode(),
                                        ex });
            throw AdapterUtil.mapException(this, ex);
        } catch (NullPointerException nullX) {
            throw runtimeXIfNotClosed(nullX);
        }
    }

    public float getFloat(int i) throws SQLException {
        try {
            return cstmtImpl.getFloat(i);
        } catch (SQLException ex) {
            if (tc.isDebugEnabled())
                Tr.debug(tc, "getFloat",
                         new Object[] {
                                        "SQL STATE:  " + ex.getSQLState(),
                                        "ERROR CODE: " + ex.getErrorCode(),
                                        ex });
            throw AdapterUtil.mapException(this, ex);
        } catch (NullPointerException nullX) {
            throw runtimeXIfNotClosed(nullX);
        }
    }

    public int getInt(int i) throws SQLException {
        try {
            return cstmtImpl.getInt(i);
        } catch (SQLException ex) {
            if (tc.isDebugEnabled())
                Tr.debug(tc, "getInt",
                         new Object[] {
                                        "SQL STATE:  " + ex.getSQLState(),
                                        "ERROR CODE: " + ex.getErrorCode(),
                                        ex });
            throw AdapterUtil.mapException(this, ex);
        } catch (NullPointerException nullX) {
            throw runtimeXIfNotClosed(nullX);
        }
    }

    public long getLong(int i) throws SQLException {
        try {
            return cstmtImpl.getLong(i);
        } catch (SQLException ex) {
            if (tc.isDebugEnabled())
                Tr.debug(
                         tc, "getLong",
                         new Object[] {
                                        "SQL STATE:  " + ex.getSQLState(),
                                        "ERROR CODE: " + ex.getErrorCode(),
                                        ex });
            throw AdapterUtil.mapException(this, ex);
        } catch (NullPointerException nullX) {
            throw runtimeXIfNotClosed(nullX);
        }
    }

    public Object getObject(int i) throws SQLException {
        try {
            return cstmtImpl.getObject(i);
        } catch (SQLException ex) {
            if (tc.isDebugEnabled())
                Tr.debug(
                         tc,
                         "getObject",
                         new Object[] {
                                        "SQL STATE:  " + ex.getSQLState(),
                                        "ERROR CODE: " + ex.getErrorCode(),
                                        ex });
            throw AdapterUtil.mapException(this, ex);
        } catch (NullPointerException nullX) {
            throw runtimeXIfNotClosed(nullX);
        }
    }

    public Object getObject(int i, java.util.Map map) throws SQLException {
        try {
            return cstmtImpl.getObject(i, map);
        } catch (SQLException ex) {
            if (tc.isDebugEnabled())
                Tr.debug(
                         tc,
                         "getObject",
                         new Object[] {
                                        "SQL STATE:  " + ex.getSQLState(),
                                        "ERROR CODE: " + ex.getErrorCode(),
                                        ex });
            throw AdapterUtil.mapException(this, ex);
        } catch (NullPointerException nullX) {
            throw runtimeXIfNotClosed(nullX);
        }
    }

    public Ref getRef(int i) throws SQLException {
        try {
            return cstmtImpl.getRef(i);
        } catch (SQLException ex) {
            if (tc.isDebugEnabled())
                Tr.debug(
                         tc,
                         "getRef",
                         new Object[] {
                                        "SQL STATE:  " + ex.getSQLState(),
                                        "ERROR CODE: " + ex.getErrorCode(),
                                        ex });
            throw AdapterUtil.mapException(this, ex);
        } catch (NullPointerException nullX) {
            throw runtimeXIfNotClosed(nullX);
        }
    }

    public short getShort(int i) throws SQLException {
        try {
            return cstmtImpl.getShort(i);
        } catch (SQLException ex) {
            if (tc.isDebugEnabled())
                Tr.debug(
                         tc,
                         "getShort",
                         new Object[] {
                                        "SQL STATE:  " + ex.getSQLState(),
                                        "ERROR CODE: " + ex.getErrorCode(),
                                        ex });
            throw AdapterUtil.mapException(this, ex);
        } catch (NullPointerException nullX) {
            throw runtimeXIfNotClosed(nullX);
        }
    }

    public String getString(int i) throws SQLException {
        try {
            return cstmtImpl.getString(i);
        } catch (SQLException ex) {
            if (tc.isDebugEnabled())
                Tr.debug(
                         tc,
                         "getString",
                         new Object[] {
                                        "SQL STATE:  " + ex.getSQLState(),
                                        "ERROR CODE: " + ex.getErrorCode(),
                                        ex });
            throw AdapterUtil.mapException(this, ex);
        } catch (NullPointerException nullX) {
            throw runtimeXIfNotClosed(nullX);
        }
    }

    public Time getTime(int i) throws SQLException {
        try {
            return cstmtImpl.getTime(i);
        } catch (SQLException ex) {
            if (tc.isDebugEnabled())
                Tr.debug(
                         tc,
                         "getTime",
                         new Object[] {
                                        "SQL STATE:  " + ex.getSQLState(),
                                        "ERROR CODE: " + ex.getErrorCode(),
                                        ex });
            throw AdapterUtil.mapException(this, ex);
        } catch (NullPointerException nullX) {
            throw runtimeXIfNotClosed(nullX);
        }
    }

    public Time getTime(int i, Calendar cal) throws SQLException {
        try {
            return cstmtImpl.getTime(i, cal);
        } catch (SQLException ex) {
            if (tc.isDebugEnabled())
                Tr.debug(
                         tc,
                         "getTime",
                         new Object[] {
                                        "SQL STATE:  " + ex.getSQLState(),
                                        "ERROR CODE: " + ex.getErrorCode(),
                                        ex });
            throw AdapterUtil.mapException(this, ex);
        } catch (NullPointerException nullX) {
            throw runtimeXIfNotClosed(nullX);
        }
    }

    public Timestamp getTimestamp(int i) throws SQLException {
        try {
            return cstmtImpl.getTimestamp(i);
        } catch (SQLException ex) {
            if (tc.isDebugEnabled())
                Tr.debug(
                         tc,
                         "getTimestamp",
                         new Object[] {
                                        "SQL STATE:  " + ex.getSQLState(),
                                        "ERROR CODE: " + ex.getErrorCode(),
                                        ex });
            throw AdapterUtil.mapException(this, ex);
        } catch (NullPointerException nullX) {
            throw runtimeXIfNotClosed(nullX);
        }
    }

    public Timestamp getTimestamp(int i, Calendar cal) throws SQLException {
        try {
            return cstmtImpl.getTimestamp(i, cal);
        } catch (SQLException ex) {
            if (tc.isDebugEnabled())
                Tr.debug(
                         tc,
                         "getTimestamp",
                         new Object[] {
                                        "SQL STATE:  " + ex.getSQLState(),
                                        "ERROR CODE: " + ex.getErrorCode(),
                                        ex });
            throw AdapterUtil.mapException(this, ex);
        } catch (NullPointerException nullX) {
            throw runtimeXIfNotClosed(nullX);
        }
    }

    /**
     * @return the trace component for the JdbcCallableStatement.
     */
    final TraceComponent getTracer() {
        return tc;
    }

    public void registerOutParameter(int i, int sqlType) throws SQLException {
        if (tc.isDebugEnabled())
            Tr.debug(
                     tc,
                     "registerOutParameter",
                     new Object[] {
                                    this,
                                    new Integer(i),
                                    AdapterUtil.getSQLTypeString(sqlType) });

        try {
            cstmtImpl.registerOutParameter(i, sqlType);
        } catch (SQLException ex) {
            if (tc.isDebugEnabled())
                Tr.debug(
                         tc,
                         "setUnicodeStream",
                         new Object[] {
                                        "SQL STATE:  " + ex.getSQLState(),
                                        "ERROR CODE: " + ex.getErrorCode(),
                                        ex });
            throw AdapterUtil.mapException(this, ex);
        } catch (NullPointerException nullX) {
            throw runtimeXIfNotClosed(nullX);
        }
    }

    public void registerOutParameter(int i, int sqlType, int scale) throws SQLException {
        if (tc.isDebugEnabled())
            Tr.debug(
                     tc,
                     "registerOutParameter",
                     new Object[] {
                                    this,
                                    new Integer(i),
                                    AdapterUtil.getSQLTypeString(sqlType),
                                    new Integer(scale) });

        try {
            cstmtImpl.registerOutParameter(i, sqlType, scale);
        } catch (SQLException ex) {
            if (tc.isDebugEnabled())
                Tr.debug(
                         tc,
                         "registerOutParameter",
                         new Object[] {
                                        "SQL STATE:  " + ex.getSQLState(),
                                        "ERROR CODE: " + ex.getErrorCode(),
                                        ex });
            throw AdapterUtil.mapException(this, ex);
        } catch (NullPointerException nullX) {
            throw runtimeXIfNotClosed(nullX);
        }
    }

    public void registerOutParameter(int i, int sqlType, String typeName) throws SQLException {
        if (tc.isDebugEnabled())
            Tr.debug(
                     tc,
                     "registerOutParameter",
                     new Object[] {
                                    this,
                                    new Integer(i),
                                    AdapterUtil.getSQLTypeString(sqlType),
                                    typeName });

        try {
            cstmtImpl.registerOutParameter(i, sqlType, typeName);
        } catch (SQLException ex) {
            if (tc.isDebugEnabled())
                Tr.debug(
                         tc,
                         "registerOutParameter",
                         new Object[] {
                                        "SQL STATE:  " + ex.getSQLState(),
                                        "ERROR CODE: " + ex.getErrorCode(),
                                        ex });
            throw AdapterUtil.mapException(this, ex);
        } catch (NullPointerException nullX) {
            throw runtimeXIfNotClosed(nullX);
        }
    }

    public boolean wasNull() throws SQLException {
        try {
            return cstmtImpl.wasNull();
        } catch (SQLException ex) {
            if (tc.isDebugEnabled())
                Tr.debug(
                         tc,
                         "wasNull",
                         new Object[] {
                                        "SQL STATE:  " + ex.getSQLState(),
                                        "ERROR CODE: " + ex.getErrorCode(),
                                        ex });
            throw AdapterUtil.mapException(this, ex);
        } catch (NullPointerException nullX) {
            throw runtimeXIfNotClosed(nullX);
        }
    }

    public void registerOutParameter(String parameterName, int sqlType) throws SQLException {
    }

    public void registerOutParameter(
                                     String parameterName,
                                     int sqlType,
                                     int scale) throws SQLException {
    }

    public void registerOutParameter(
                                     String parameterName,
                                     int sqlType,
                                     String typeName) throws SQLException {
    }

    public void setURL(String parameterName, java.net.URL val) throws SQLException {
    }

    public void setNull(String parameterName, int sqlType) throws SQLException {
    }

    public void setBoolean(String parameterName, boolean x) throws SQLException {
    }

    public void setByte(String parameterName, byte x) throws SQLException {
    }

    public void setShort(String parameterName, short x) throws SQLException {
    }

    public void setInt(String parameterName, int x) throws SQLException {
    }

    public void setLong(String parameterName, long x) throws SQLException {
    }

    public void setFloat(String parameterName, float x) throws SQLException {
    }

    public void setDouble(String parameterName, double x) throws SQLException {
    }

    public void setBigDecimal(String parameterName, java.math.BigDecimal x) throws SQLException {
    }

    public void setString(String parameterName, String x) throws SQLException {
    }

    public void setBytes(String parameterName, byte[] x) throws SQLException {
    }

    public void setDate(String parameterName, java.sql.Date x) throws SQLException {
    }

    public void setTime(String parameterName, java.sql.Time x) throws SQLException {
    }

    public void setTimestamp(String parameterName, java.sql.Timestamp x) throws SQLException {
    }

    public void setAsciiStream(
                               String parameterName,
                               java.io.InputStream x,
                               int length) throws SQLException {
    }

    public void setBinaryStream(
                                String parameterName,
                                java.io.InputStream x,
                                int length) throws SQLException {
    }

    public void setObject(
                          String parameterName,
                          Object x,
                          int targetSqlType,
                          int scale) throws SQLException {
    }

    public void setObject(String parameterName, Object x, int targetSqlType) throws SQLException {
    }

    public void setObject(String parameterName, Object x) throws SQLException {
    }

    public void setCharacterStream(
                                   String parameterName,
                                   java.io.Reader reader,
                                   int length) throws SQLException {
    }

    public void setDate(
                        String parameterName,
                        java.sql.Date x,
                        java.util.Calendar cal) throws SQLException {
    }

    public void setTime(
                        String parameterName,
                        java.sql.Time x,
                        java.util.Calendar cal) throws SQLException {
    }

    public void setTimestamp(
                             String parameterName,
                             java.sql.Timestamp x,
                             java.util.Calendar cal) throws SQLException {
    }

    public void setNull(String parameterName, int sqlType, String typeName) throws SQLException {
    }

    public Array getArray(String parameterName) throws SQLException {
        return null;
    }

    public java.math.BigDecimal getBigDecimal(String parameterName) throws SQLException {
        return null;
    }

    public java.sql.Blob getBlob(String parameterName) throws SQLException {
        return null;
    }

    public boolean getBoolean(String parameterName) throws SQLException {
        return false;
    }

    public byte getByte(String parameterName) throws SQLException {
        return 0;
    }

    public byte[] getBytes(String parameterName) throws SQLException {
        return null;
    }

    public java.sql.Clob getClob(String parameterName) throws SQLException {
        return null;
    }

    public java.sql.Date getDate(String parameterName) throws SQLException {
        return null;
    }

    public java.sql.Date getDate(String parameterName, Calendar cal) throws SQLException {
        return null;
    }

    public double getDouble(String parameterName) throws SQLException {
        return 0;
    }

    public float getFloat(String parameterName) throws SQLException {
        return 0;
    }

    public int getInt(String parameterName) throws SQLException {
        return 0;
    }

    public long getLong(String parameterName) throws SQLException {
        return 0;
    }

    public Object getObject(String parameterName) throws SQLException {
        return null;
    }

    public Object getObject(String parameterName, java.util.Map map) throws SQLException {
        return null;
    }

    public java.sql.Ref getRef(String parameterName) throws SQLException {
        return null;
    }

    public short getShort(String parameterName) throws SQLException {
        return 0;
    }

    public String getString(String s) throws SQLException {
        return null;
    }

    public java.sql.Time getTime(String parameterName) throws SQLException {
        return null;
    }

    public java.sql.Time getTime(String parameterName, Calendar cal) throws SQLException {
        return null;
    }

    public java.sql.Timestamp getTimestamp(String parameterName) throws SQLException {
        return null;
    }

    public java.sql.Timestamp getTimestamp(String parameterName, Calendar cal) throws SQLException {
        return null;
    }

    public java.net.URL getURL(int parameterIndex) throws SQLException {
        return null;
    }

    public java.net.URL getURL(String parameterName) throws SQLException {
        return null;
    }

    public Reader getCharacterStream(int parameterIndex) throws SQLException {
        return null;
    }

    public Reader getCharacterStream(String parameterName) throws SQLException {
        return null;
    }

    public Reader getNCharacterStream(int parameterIndex) throws SQLException {
        return null;
    }

    public Reader getNCharacterStream(String parameterName) throws SQLException {
        return null;
    }

    public NClob getNClob(int parameterIndex) throws SQLException {
        return null;
    }

    public NClob getNClob(String parameterName) throws SQLException {
        return null;
    }

    public String getNString(int parameterIndex) throws SQLException {
        return null;
    }

    public String getNString(String parameterName) throws SQLException {
        return null;
    }

    public RowId getRowId(int parameterIndex) throws SQLException {
        return null;
    }

    public RowId getRowId(String parameterName) throws SQLException {
        return null;
    }

    public SQLXML getSQLXML(int parameterIndex) throws SQLException {
        return null;
    }

    public SQLXML getSQLXML(String parameterName) throws SQLException {
        return null;
    }

    public void setAsciiStream(String parameterName, InputStream x, long length) throws SQLException {
    }

    public void setBinaryStream(String parameterName, InputStream x, long length) throws SQLException {
    }

    public void setBlob(String parameterName, Blob x) throws SQLException {
    }

    public void setBlob(String parameterName, InputStream inputStream, long length) throws SQLException {
    }

    public void setCharacterStream(String parameterName, Reader reader, long length) throws SQLException {
    }

    public void setClob(String parameterName, Clob x) throws SQLException {
    }

    public void setClob(String parameterName, Reader reader, long length) throws SQLException {
    }

    public void setNCharacterStream(String parameterName, Reader value, long length) throws SQLException {
    }

    public void setNClob(String parameterName, NClob value) throws SQLException {
    }

    public void setNClob(String parameterName, Reader reader, long length) throws SQLException {
    }

    public void setNString(String parameterName, String value) throws SQLException {
    }

    public void setRowId(String parameterName, RowId x) throws SQLException {
    }

    public void setSQLXML(String parameterName, SQLXML xmlObject) throws SQLException {
    }

    public void setNClob(java.lang.String x, java.io.Reader reader) throws SQLException {
    }

    public void setBlob(java.lang.String x, java.io.InputStream inputstream) throws SQLException {
    }

    public void setAsciiStream(String parameterName, InputStream x) throws SQLException {
    }

    public void setBinaryStream(String parameterName, InputStream x) throws SQLException {
    }

    public void setCharacterStream(String parameterName, Reader reader) throws SQLException {
    }

    public void setNCharacterStream(String parameterName, Reader value) throws SQLException {
    }

    public void setClob(String parameterName, Reader reader) throws SQLException {
    }

    //@Override // Java 7
    public <T> T getObject(int parameterIndex, Class<T> type) {
        throw new UnsupportedOperationException();
    }

    //@Override // Java 7
    public <T> T getObject(String parameterName, Class<T> type) {
        throw new UnsupportedOperationException();
    }
}
