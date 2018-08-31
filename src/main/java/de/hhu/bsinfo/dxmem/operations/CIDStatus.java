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

package de.hhu.bsinfo.dxmem.operations;

import de.hhu.bsinfo.dxmem.core.Context;
import de.hhu.bsinfo.dxmem.data.ChunkIDRanges;

public class CIDStatus {
    private final Context m_context;

    public CIDStatus(final Context p_context) {
        m_context = p_context;
    }

    /**
     * Returns the highest LocalID currently in use
     *
     * @return the LocalID
     */
    public long getHighestUsedLocalID() {
        return m_context.getLIDStore().getCurrentHighestLID();
    }

    public ChunkIDRanges getCIDRangesOfLocalChunks() {
        return getCIDRangesOfChunks(m_context.getNodeId());
    }

    public ChunkIDRanges getCIDRangesOfChunks() {
        m_context.getDefragmenter().acquireApplicationThreadLock();

        ChunkIDRanges ranges = m_context.getCIDTable().getCIDRangesOfAllChunks();

        m_context.getDefragmenter().releaseApplicationThreadLock();

        return ranges;
    }

    public ChunkIDRanges getCIDRangesOfChunks(final short p_nodeId) {
        m_context.getDefragmenter().acquireApplicationThreadLock();

        ChunkIDRanges ranges = m_context.getCIDTable().getCIDRangesOfAllChunks(p_nodeId);

        m_context.getDefragmenter().releaseApplicationThreadLock();

        return ranges;
    }

    public ChunkIDRanges getAllMigratedChunkIDRanges() {
        m_context.getDefragmenter().acquireApplicationThreadLock();

        ChunkIDRanges ranges = m_context.getCIDTable().getCIDRangesOfAllMigratedChunks();

        m_context.getDefragmenter().releaseApplicationThreadLock();

        return ranges;
    }
}
