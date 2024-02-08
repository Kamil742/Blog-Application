import React, { useState } from 'react';
import styles from "./techStack.module.css"

interface Version {
    technology: string;
    version: string;
}

const Techstack = () => {
 const [selectedTech, setSelectedTech] = useState<string | null>(null);
  const [selectedVersion, setSelectedVersion] = useState<string | null>(null);

  const technologies = ['React', 'Angular', 'Vue']; 
  const versions: Record<string, string[]> = {
    React: ['16.0', '17.0'],
    Angular: ['2.0', '4.0', '8.0'],
    Vue: ['2.0', '3.0'],
  };

  const handleTechChange = (tech: string) => {
    setSelectedTech(tech);
    setSelectedVersion(null);
  };

  const handleVersionChange = (version: string) => {
    setSelectedVersion(version);
  };

  return (
      <div >
           Tech Stack
         <div className={styles.tech}> 
     
      <select onChange={(e) => handleTechChange(e.target.value)}>
        <option className={styles.container} value="">Select TechStack</option>
        {technologies.map((tech) => (
          <option key={tech} value={tech}>
            {tech}
            </option>
            
        ))}
              </select>
              </div>

      {selectedTech && (
        <div>
          Version
          <select onChange={(e) => handleVersionChange(e.target.value)}>
            <option value="" >Select </option>
            {versions[selectedTech].map((version) => (
              <option key={version} value={version}>
                {version}
              </option>
            ))}
          </select>
        </div>
      )}

      <div>
        <div>Selected Tech</div>
        <input
          type="text"
          readOnly
          value={`${selectedTech || ''} ${selectedVersion || ''}`}
        />
      </div>
    </div>
  )
}

export default Techstack
