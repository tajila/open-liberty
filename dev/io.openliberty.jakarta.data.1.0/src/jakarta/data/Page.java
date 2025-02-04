/*******************************************************************************
 * Copyright (c) 2022 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package jakarta.data;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import jakarta.data.repository.Pageable;

/**
 * Method signatures copied from proposals in the Jakarta Data git repo.
 */
public interface Page<T> extends Supplier<Stream<T>> {
    List<T> getContent();

    <C extends Collection<T>> C getContent(Supplier<C> collectionFactory);

    long getPage();

    Pageable getPageable();

    Pageable next();

    long size();
}
