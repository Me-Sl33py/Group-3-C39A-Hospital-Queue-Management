import re

with open("src/view/DoctorPanel.form", "r") as f:
    content = f.read()

# Replace Horizontal Layout for panelPatientInfo
horizontal_old = """                          <Component id="jTextField2" min="-2" pref="315" max="-2" attributes="0"/>
                          <EmptySpace max="32767" attributes="0"/>"""
horizontal_new = """                          <Component id="jTextField2" min="-2" pref="250" max="-2" attributes="0"/>
                          <EmptySpace type="unrelated" max="-2" attributes="0"/>
                          <Component id="btnSearchPatient" min="-2" pref="100" max="-2" attributes="0"/>
                          <EmptySpace max="32767" attributes="0"/>"""
content = content.replace(horizontal_old, horizontal_new)

# Replace Vertical Layout for panelPatientInfo
vertical_old = """                              <Component id="jTextField1" alignment="3" min="-2" max="-2" attributes="0"/>
                              <Component id="jTextField2" alignment="3" min="-2" max="-2" attributes="0"/>
                          </Group>"""
vertical_new = """                              <Component id="jTextField1" alignment="3" min="-2" max="-2" attributes="0"/>
                              <Component id="jTextField2" alignment="3" min="-2" max="-2" attributes="0"/>
                              <Component id="btnSearchPatient" alignment="3" min="-2" pref="30" max="-2" attributes="0"/>
                          </Group>"""
content = content.replace(vertical_old, vertical_new)

# Add btnSearchPatient to SubComponents
subcomp_old = """                <Component class="javax.swing.JTextField" name="jTextField2">
                </Component>
              </SubComponents>"""
subcomp_new = """                <Component class="javax.swing.JTextField" name="jTextField2">
                </Component>
                <Component class="javax.swing.JButton" name="btnSearchPatient">
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
                    <Property name="text" type="java.lang.String" value="Search"/>
                  </Properties>
                  <Events>
                    <EventHandler event="actionPerformed" listener="java.awt.event.ActionListener" parameters="java.awt.event.ActionEvent" handler="btnSearchPatientActionPerformed"/>
                  </Events>
                </Component>
              </SubComponents>"""
content = content.replace(subcomp_old, subcomp_new)

with open("src/view/DoctorPanel.form", "w") as f:
    f.write(content)

print("Patched XML")
