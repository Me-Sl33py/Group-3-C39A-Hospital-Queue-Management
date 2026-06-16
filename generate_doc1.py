import re

form_xml = """
              <Layout>
                <DimensionLayout dim="0">
                  <Group type="103" groupAlignment="0" attributes="0">
                      <Group type="102" alignment="0" attributes="0">
                          <EmptySpace min="-2" pref="20" max="-2" attributes="0"/>
                          <Group type="103" groupAlignment="0" attributes="0">
                              <Component id="lblDocTitle1" alignment="0" min="-2" max="-2" attributes="0"/>
                              <Component id="jSeparator4" alignment="0" max="32767" attributes="0"/>
                              
                              <Group type="102" alignment="0" attributes="0">
                                  <Group type="103" groupAlignment="0" attributes="0">
                                      <Component id="lblDocPatientId" min="-2" max="-2" attributes="0"/>
                                      <Component id="txtDocPatientId" min="-2" pref="150" max="-2" attributes="0"/>
                                  </Group>
                                  <EmptySpace min="-2" pref="20" max="-2" attributes="0"/>
                                  <Group type="103" groupAlignment="0" attributes="0">
                                      <Component id="lblDocPatientName" min="-2" max="-2" attributes="0"/>
                                      <Component id="txtDocPatientName" min="-2" pref="250" max="-2" attributes="0"/>
                                  </Group>
                              </Group>

                              <Group type="102" alignment="0" attributes="0">
                                  <Group type="103" groupAlignment="0" attributes="0">
                                      <Component id="lblDocApptDate" min="-2" max="-2" attributes="0"/>
                                      <Component id="txtDocApptDate" min="-2" pref="150" max="-2" attributes="0"/>
                                  </Group>
                                  <EmptySpace min="-2" pref="20" max="-2" attributes="0"/>
                                  <Group type="103" groupAlignment="0" attributes="0">
                                      <Component id="lblDocApptTime" min="-2" max="-2" attributes="0"/>
                                      <Component id="txtDocApptTime" min="-2" pref="150" max="-2" attributes="0"/>
                                  </Group>
                              </Group>

                              <Component id="lblDocDiagnosis" alignment="0" min="-2" max="-2" attributes="0"/>
                              <Component id="scrollPaneDiagnosis" alignment="0" max="32767" attributes="0"/>

                              <Component id="lblDocPrescription" alignment="0" min="-2" max="-2" attributes="0"/>
                              <Component id="scrollPanePrescription" alignment="0" max="32767" attributes="0"/>

                              <Component id="lblDocNotes" alignment="0" min="-2" max="-2" attributes="0"/>
                              <Component id="scrollPaneNotes" alignment="0" max="32767" attributes="0"/>

                              <Component id="lblDocFollowUp" alignment="0" min="-2" max="-2" attributes="0"/>
                              <Component id="jDateChooserFollowUp" alignment="0" min="-2" pref="200" max="-2" attributes="0"/>

                              <Component id="jSeparator5" alignment="0" max="32767" attributes="0"/>
                              <Group type="102" alignment="0" attributes="0">
                                  <Component id="btnCancel1" min="-2" pref="90" max="-2" attributes="0"/>
                                  <EmptySpace type="unrelated" max="-2" attributes="0"/>
                                  <Component id="btnSubmit1" min="-2" pref="130" max="-2" attributes="0"/>
                              </Group>
                          </Group>
                          <EmptySpace min="-2" pref="20" max="-2" attributes="0"/>
                      </Group>
                  </Group>
                </DimensionLayout>
                <DimensionLayout dim="1">
                  <Group type="103" groupAlignment="0" attributes="0">
                      <Group type="102" alignment="0" attributes="0">
                          <EmptySpace min="-2" pref="15" max="-2" attributes="0"/>
                          <Component id="lblDocTitle1" min="-2" max="-2" attributes="0"/>
                          <EmptySpace max="-2" attributes="0"/>
                          <Component id="jSeparator4" min="-2" pref="3" max="-2" attributes="0"/>
                          <EmptySpace min="-2" pref="10" max="-2" attributes="0"/>
                          
                          <Group type="103" groupAlignment="3" attributes="0">
                              <Component id="lblDocPatientId" min="-2" max="-2" attributes="0"/>
                              <Component id="lblDocPatientName" min="-2" max="-2" attributes="0"/>
                          </Group>
                          <EmptySpace max="-2" attributes="0"/>
                          <Group type="103" groupAlignment="3" attributes="0">
                              <Component id="txtDocPatientId" min="-2" max="-2" attributes="0"/>
                              <Component id="txtDocPatientName" min="-2" max="-2" attributes="0"/>
                          </Group>
                          <EmptySpace min="-2" pref="10" max="-2" attributes="0"/>

                          <Group type="103" groupAlignment="3" attributes="0">
                              <Component id="lblDocApptDate" min="-2" max="-2" attributes="0"/>
                              <Component id="lblDocApptTime" min="-2" max="-2" attributes="0"/>
                          </Group>
                          <EmptySpace max="-2" attributes="0"/>
                          <Group type="103" groupAlignment="3" attributes="0">
                              <Component id="txtDocApptDate" min="-2" max="-2" attributes="0"/>
                              <Component id="txtDocApptTime" min="-2" max="-2" attributes="0"/>
                          </Group>
                          <EmptySpace min="-2" pref="10" max="-2" attributes="0"/>

                          <Component id="lblDocDiagnosis" min="-2" max="-2" attributes="0"/>
                          <EmptySpace max="-2" attributes="0"/>
                          <Component id="scrollPaneDiagnosis" min="-2" pref="60" max="-2" attributes="0"/>
                          <EmptySpace min="-2" pref="10" max="-2" attributes="0"/>

                          <Component id="lblDocPrescription" min="-2" max="-2" attributes="0"/>
                          <EmptySpace max="-2" attributes="0"/>
                          <Component id="scrollPanePrescription" min="-2" pref="60" max="-2" attributes="0"/>
                          <EmptySpace min="-2" pref="10" max="-2" attributes="0"/>

                          <Component id="lblDocNotes" min="-2" max="-2" attributes="0"/>
                          <EmptySpace max="-2" attributes="0"/>
                          <Component id="scrollPaneNotes" min="-2" pref="60" max="-2" attributes="0"/>
                          <EmptySpace min="-2" pref="10" max="-2" attributes="0"/>

                          <Component id="lblDocFollowUp" min="-2" max="-2" attributes="0"/>
                          <EmptySpace max="-2" attributes="0"/>
                          <Component id="jDateChooserFollowUp" min="-2" max="-2" attributes="0"/>
                          <EmptySpace min="-2" pref="15" max="-2" attributes="0"/>

                          <Component id="jSeparator5" min="-2" max="-2" attributes="0"/>
                          <EmptySpace min="-2" pref="10" max="-2" attributes="0"/>
                          <Group type="103" groupAlignment="3" attributes="0">
                              <Component id="btnCancel1" alignment="3" min="-2" pref="30" max="-2" attributes="0"/>
                              <Component id="btnSubmit1" alignment="3" min="-2" pref="30" max="-2" attributes="0"/>
                          </Group>
                          <EmptySpace min="-2" pref="15" max="-2" attributes="0"/>
                      </Group>
                  </Group>
                </DimensionLayout>
              </Layout>
              <SubComponents>
                <Component class="javax.swing.JLabel" name="lblDocTitle1">
                  <Properties>
                    <Property name="font" type="java.awt.Font" editor="org.netbeans.beaninfo.editors.FontEditor">
                      <Font name="Segoe UI" size="14" style="1"/>
                    </Property>
                    <Property name="foreground" type="java.awt.Color" editor="org.netbeans.beaninfo.editors.ColorEditor">
                      <Color blue="3b" green="29" red="1e" type="rgb"/>
                    </Property>
                    <Property name="text" type="java.lang.String" value="Clinical Documentation"/>
                  </Properties>
                </Component>
                <Component class="javax.swing.JSeparator" name="jSeparator4">
                  <Properties>
                    <Property name="foreground" type="java.awt.Color" editor="org.netbeans.beaninfo.editors.ColorEditor">
                      <Color blue="cc" green="cc" red="cc" type="rgb"/>
                    </Property>
                  </Properties>
                </Component>

                <Component class="javax.swing.JLabel" name="lblDocPatientId">
                  <Properties>
                    <Property name="font" type="java.awt.Font" editor="org.netbeans.beaninfo.editors.FontEditor"><Font name="Segoe UI" size="12" style="1"/></Property>
                    <Property name="text" type="java.lang.String" value="Patient ID"/>
                  </Properties>
                </Component>
                <Component class="javax.swing.JTextField" name="txtDocPatientId">
                  <Properties>
                    <Property name="editable" type="boolean" value="false"/>
                  </Properties>
                </Component>

                <Component class="javax.swing.JLabel" name="lblDocPatientName">
                  <Properties>
                    <Property name="font" type="java.awt.Font" editor="org.netbeans.beaninfo.editors.FontEditor"><Font name="Segoe UI" size="12" style="1"/></Property>
                    <Property name="text" type="java.lang.String" value="Patient Name"/>
                  </Properties>
                </Component>
                <Component class="javax.swing.JTextField" name="txtDocPatientName">
                  <Properties>
                    <Property name="editable" type="boolean" value="false"/>
                  </Properties>
                </Component>

                <Component class="javax.swing.JLabel" name="lblDocApptDate">
                  <Properties>
                    <Property name="font" type="java.awt.Font" editor="org.netbeans.beaninfo.editors.FontEditor"><Font name="Segoe UI" size="12" style="1"/></Property>
                    <Property name="text" type="java.lang.String" value="Appointment Date"/>
                  </Properties>
                </Component>
                <Component class="javax.swing.JTextField" name="txtDocApptDate">
                  <Properties>
                    <Property name="editable" type="boolean" value="false"/>
                  </Properties>
                </Component>

                <Component class="javax.swing.JLabel" name="lblDocApptTime">
                  <Properties>
                    <Property name="font" type="java.awt.Font" editor="org.netbeans.beaninfo.editors.FontEditor"><Font name="Segoe UI" size="12" style="1"/></Property>
                    <Property name="text" type="java.lang.String" value="Appointment Time"/>
                  </Properties>
                </Component>
                <Component class="javax.swing.JTextField" name="txtDocApptTime">
                  <Properties>
                    <Property name="editable" type="boolean" value="false"/>
                  </Properties>
                </Component>

                <Component class="javax.swing.JLabel" name="lblDocDiagnosis">
                  <Properties>
                    <Property name="font" type="java.awt.Font" editor="org.netbeans.beaninfo.editors.FontEditor"><Font name="Segoe UI" size="12" style="1"/></Property>
                    <Property name="text" type="java.lang.String" value="Diagnosis"/>
                  </Properties>
                </Component>
                <Container class="javax.swing.JScrollPane" name="scrollPaneDiagnosis">
                  <SubComponents>
                    <Component class="javax.swing.JTextArea" name="taDiagnosis">
                      <Properties>
                        <Property name="columns" type="int" value="20"/>
                        <Property name="rows" type="int" value="3"/>
                      </Properties>
                    </Component>
                  </SubComponents>
                </Container>

                <Component class="javax.swing.JLabel" name="lblDocPrescription">
                  <Properties>
                    <Property name="font" type="java.awt.Font" editor="org.netbeans.beaninfo.editors.FontEditor"><Font name="Segoe UI" size="12" style="1"/></Property>
                    <Property name="text" type="java.lang.String" value="Prescription"/>
                  </Properties>
                </Component>
                <Container class="javax.swing.JScrollPane" name="scrollPanePrescription">
                  <SubComponents>
                    <Component class="javax.swing.JTextArea" name="taPrescription">
                      <Properties>
                        <Property name="columns" type="int" value="20"/>
                        <Property name="rows" type="int" value="3"/>
                      </Properties>
                    </Component>
                  </SubComponents>
                </Container>

                <Component class="javax.swing.JLabel" name="lblDocNotes">
                  <Properties>
                    <Property name="font" type="java.awt.Font" editor="org.netbeans.beaninfo.editors.FontEditor"><Font name="Segoe UI" size="12" style="1"/></Property>
                    <Property name="text" type="java.lang.String" value="Notes (Optional)"/>
                  </Properties>
                </Component>
                <Container class="javax.swing.JScrollPane" name="scrollPaneNotes">
                  <SubComponents>
                    <Component class="javax.swing.JTextArea" name="taNotes">
                      <Properties>
                        <Property name="columns" type="int" value="20"/>
                        <Property name="rows" type="int" value="3"/>
                      </Properties>
                    </Component>
                  </SubComponents>
                </Container>

                <Component class="javax.swing.JLabel" name="lblDocFollowUp">
                  <Properties>
                    <Property name="font" type="java.awt.Font" editor="org.netbeans.beaninfo.editors.FontEditor"><Font name="Segoe UI" size="12" style="1"/></Property>
                    <Property name="text" type="java.lang.String" value="Follow Up Date (Optional)"/>
                  </Properties>
                </Component>
                <Component class="com.toedter.calendar.JDateChooser" name="jDateChooserFollowUp"/>

                <Component class="javax.swing.JSeparator" name="jSeparator5">
                  <Properties>
                    <Property name="foreground" type="java.awt.Color" editor="org.netbeans.beaninfo.editors.ColorEditor">
                      <Color blue="cc" green="cc" red="cc" type="rgb"/>
                    </Property>
                  </Properties>
                </Component>
                <Component class="javax.swing.JButton" name="btnCancel1">
                  <Properties>
                    <Property name="background" type="java.awt.Color" editor="org.netbeans.beaninfo.editors.ColorEditor">
                      <Color blue="ee" green="ee" red="ee" type="rgb"/>
                    </Property>
                    <Property name="font" type="java.awt.Font" editor="org.netbeans.beaninfo.editors.FontEditor">
                      <Font name="Segoe UI" size="12" style="1"/>
                    </Property>
                    <Property name="foreground" type="java.awt.Color" editor="org.netbeans.beaninfo.editors.ColorEditor">
                      <Color blue="33" green="33" red="33" type="rgb"/>
                    </Property>
                    <Property name="text" type="java.lang.String" value="Cancel"/>
                  </Properties>
                </Component>
                <Component class="javax.swing.JButton" name="btnSubmit1">
                  <Properties>
                    <Property name="background" type="java.awt.Color" editor="org.netbeans.beaninfo.editors.ColorEditor">
                      <Color blue="ff" green="66" red="0" type="rgb"/>
                    </Property>
                    <Property name="font" type="java.awt.Font" editor="org.netbeans.beaninfo.editors.FontEditor">
                      <Font name="Segoe UI" size="12" style="1"/>
                    </Property>
                    <Property name="foreground" type="java.awt.Color" editor="org.netbeans.beaninfo.editors.ColorEditor">
                      <Color blue="ff" green="ff" red="ff" type="rgb"/>
                    </Property>
                    <Property name="text" type="java.lang.String" value="Submit Record"/>
                  </Properties>
                </Component>
              </SubComponents>
"""

with open("/Users/flexx/NetBeansProjects/Group-3-C39A-Hospital-Queue-Management/src/view/DoctorPanel.form", "r") as f:
    content = f.read()

# Define boundaries to replace the contents of panelDoc1
start_marker = '<Container class="javax.swing.JPanel" name="panelDoc1">'
end_marker = '</Container>'

start_idx = content.find(start_marker)
if start_idx != -1:
    # Find the matching closing tag
    depth = 0
    i = start_idx
    while i < len(content):
        if content[i:].startswith('<Container '):
            depth += 1
            i += 11
        elif content[i:].startswith('</Container>'):
            depth -= 1
            if depth == 0:
                end_idx = i + len('</Container>')
                break
            i += 12
        else:
            i += 1
    
    panel_doc1_content = content[start_idx:end_idx]
    
    # Inside panelDoc1, replace everything between <Layout> and </SubComponents>
    layout_start = panel_doc1_content.find('<Layout>')
    sub_end = panel_doc1_content.find('</SubComponents>') + len('</SubComponents>')
    
    if layout_start != -1 and sub_end != -1:
        new_panel_doc1 = panel_doc1_content[:layout_start] + form_xml + panel_doc1_content[sub_end:]
        
        # Replace in main content
        new_content = content[:start_idx] + new_panel_doc1 + content[end_idx:]
        
        with open("/Users/flexx/NetBeansProjects/Group-3-C39A-Hospital-Queue-Management/src/view/DoctorPanel.form", "w") as f:
            f.write(new_content)
        print("Success")
    else:
        print("Could not find Layout/SubComponents inside panelDoc1")
else:
    print("Could not find panelDoc1")
