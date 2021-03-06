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

package de.hhu.bsinfo.dxmem.cli.types;

import picocli.CommandLine;

import de.hhu.bsinfo.dxmem.data.ChunkID;
import de.hhu.bsinfo.dxutils.NodeID;

/**
 * Type converter for picocli to handle chunk IDs
 *
 * @author Stefan Nothaas, stefan.nothaas@hhu.de, 31.08.2018
 */
public class TypeConverterChunkId implements CommandLine.ITypeConverter<Long> {
    @Override
    public Long convert(final String p_value) throws Exception {
        String[] args = p_value.split("/");

        if (args.length == 2) {
            return ChunkID.getChunkID(NodeID.parse(args[0]), ChunkID.parse(args[1]));
        } else {
            return ChunkID.parse(p_value);
        }
    }
}
