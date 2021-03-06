/* ========================================================================
 * PlantUML : a free UML diagram generator
 * ========================================================================
 *
 * (C) Copyright 2009-2014, Arnaud Roques
 *
 * Project Info:  http://plantuml.sourceforge.net
 * 
 * This file is part of PlantUML.
 *
 * PlantUML is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PlantUML distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public
 * License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc.
 * in the United States and other countries.]
 *
 * Original Author:  Arnaud Roques
 *
 * Revision $Revision: 9786 $
 *
 */
package net.sourceforge.plantuml.activitydiagram3;

import java.util.Set;

import net.sourceforge.plantuml.activitydiagram3.ftile.Ftile;
import net.sourceforge.plantuml.activitydiagram3.ftile.FtileFactory;
import net.sourceforge.plantuml.activitydiagram3.ftile.Swimlane;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.graphic.HtmlColor;
import net.sourceforge.plantuml.sequencediagram.NotePosition;

public class InstructionGroup implements Instruction {

	private final InstructionList list;
	private final Instruction parent;
	private final HtmlColor backColor;
	private final HtmlColor titleColor;

	private final Display test;
	private Display headerNote = Display.NULL;

	public InstructionGroup(Instruction parent, Display test, HtmlColor backColor, HtmlColor titleColor,
			Swimlane swimlane) {
		this.list = new InstructionList(swimlane);
		this.parent = parent;
		this.test = test;
		this.backColor = backColor;
		this.titleColor = titleColor;
	}

	public void add(Instruction ins) {
		list.add(ins);
	}

	public Ftile createFtile(FtileFactory factory) {
		return factory.createGroup(list.createFtile(factory), test, backColor, titleColor, headerNote);
	}

	public Instruction getParent() {
		return parent;
	}

	final public boolean kill() {
		return list.kill();
	}

	public LinkRendering getInLinkRendering() {
		return null;
	}

	public void addNote(Display note, NotePosition position) {
		if (list.isEmpty()) {
			this.headerNote = note;
			return;
		}
		list.addNote(note, position);
	}

	public Set<Swimlane> getSwimlanes() {
		return list.getSwimlanes();
	}

	public Swimlane getSwimlaneIn() {
		return list.getSwimlaneIn();
	}

	public Swimlane getSwimlaneOut() {
		return list.getSwimlaneOut();
	}

}
