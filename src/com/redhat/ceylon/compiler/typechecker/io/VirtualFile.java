package com.redhat.ceylon.compiler.typechecker.io;

import java.io.InputStream;
import java.util.List;

/**
 * @author Emmanuel Bernard <emmanuel@hibernate.org>
 */
public interface VirtualFile {
    boolean isFolder();
    String getName();
    //should it be getURI instead?
    String getPath();
    InputStream getInputStream();
    List<VirtualFile> getChildren();
}
