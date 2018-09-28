/*
 * Copyright (C) 2018 Heinrich-Heine-Universitaet Duesseldorf, Institute of Computer Science,
 * Department Operating Systems
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package de.hhu.bsinfo.dxmem.cli.debugger;

import picocli.CommandLine;

import de.hhu.bsinfo.dxmem.cli.CliContext;
import de.hhu.bsinfo.dxmem.cli.types.TypeConverterNodeId;
import de.hhu.bsinfo.dxmem.cli.types.TypeConverterStorageUnit;
import de.hhu.bsinfo.dxutils.unit.StorageUnit;

/**
 * @author Stefan Nothaas, stefan.nothaas@hhu.de, 31.08.2018
 */
@CommandLine.Command(
        name = "new",
        description = "Create a new empty dxmemory instance"
)
public class CmdNewMemory implements Runnable {
    @CommandLine.Parameters(
            index = "0",
            converter = TypeConverterNodeId.class,
            paramLabel = "nodeId",
            description = "Node id for local memory management")
    private Short m_nodeId;

    @CommandLine.Parameters(
            index = "1",
            converter = TypeConverterStorageUnit.class,
            paramLabel = "heapSize",
            description = "Total heap size in bytes or StorageUnit")
    private StorageUnit m_heapSize;

    @CommandLine.Parameters(
            index = "2",
            arity = "0..1",
            paramLabel = "disableChunkLocks",
            description = "Disable the chunk locks (see DXMem class documentation for details)")
    private boolean m_disableChunkLocks = false;

    @Override
    public void run() {
        CliContext.getInstance().newMemory(m_nodeId, m_heapSize.getBytes(), m_disableChunkLocks);
    }
}
