import xml.etree.ElementTree as ET

form_path = r'c:\Users\Dell\OneDrive\Documents\NetBeansProjects\Group-3-C39A-Hospital-Queue-Management\src\view\AssignToDoctorView.form'
tree = ET.parse(form_path)
root = tree.getroot()

# Find the panel 'pnlSelection'
pnl_selection = None
for comp in root.iter('Container'):
    if comp.attrib.get('name') == 'pnlSelection':
        pnl_selection = comp
        break

if pnl_selection is not None:
    # Remove lblRoomLabel and pnlRooms
    subcomponents = pnl_selection.find('SubComponents')
    if subcomponents is not None:
        to_remove = []
        for child in subcomponents:
            if child.attrib.get('name') in ['lblRoomLabel', 'pnlRooms']:
                to_remove.append(child)
            elif child.attrib.get('name') == 'btnAssignPatient':
                # Change gridY from 6 to 4
                constraints = child.find('Constraints')
                if constraints is not None:
                    gbl = constraints.find('Constraint')
                    if gbl is not None:
                        gbc = gbl.find('GridBagConstraints')
                        if gbc is not None:
                            gbc.set('gridY', '4')
        for r in to_remove:
            subcomponents.remove(r)
            
    tree.write(form_path, encoding='UTF-8', xml_declaration=True)
    print('Successfully modified form XML')
else:
    print('Could not find pnlSelection')
