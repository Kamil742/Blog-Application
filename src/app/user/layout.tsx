import React from "react";
// import Sidebar from "../ui/dashboard/sidebar/sidebar";
import Sidebar from '../ui/user/dashboard/sidebar/sidebar'
import Navbar from "../ui/user/dashboard/navbar/navbar";
import styles from "../ui/user/dashboard/dashboard.module.css"


const layout = ({
    children,
}: {
    children: React.ReactNode
}) => {
  return (
    <div className={styles.container}>
      <div className={styles.menu}>
        <Sidebar></Sidebar>
      </div>
      <div className={styles.content}>
        <Navbar></Navbar>
        {children}
      </div>
    </div>
  );
};

export default layout;
