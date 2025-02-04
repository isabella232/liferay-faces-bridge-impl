/**
 * Copyright (c) 2000-2021 Liferay, Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.liferay.faces.bridge.context.internal;

import java.io.Writer;
import java.util.List;


/**
 * This abstract class represents a <em>capturing</em> {@link Writer}, meaning a writer that captures write operations
 * (and associated data) rather than writing them to an output stream.
 *
 * @author  Neil Griffin
 */
public abstract class CapturingWriter extends Writer {

	public abstract List<WriterOperation> getWriterOperations();
}
